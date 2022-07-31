package org.saynotobugs.quality.quality.core;

import org.dmfs.jems2.Pair;
import org.dmfs.jems2.Predicate;
import org.dmfs.jems2.iterable.Expanded;
import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.Numbered;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.jems2.pair.ValuePair;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.quality.Assessment;
import org.saynotobugs.quality.Description;
import org.saynotobugs.quality.Quality;
import org.saynotobugs.quality.description.Delimited;
import org.saynotobugs.quality.description.StructuredDescription;
import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.description.ValueDescription;
import org.saynotobugs.quality.utils.UnPaired;
import org.saynotobugs.quality.assessment.AllPassed;
import org.saynotobugs.quality.assessment.PassIf;

import java.util.Comparator;

import static org.saynotobugs.quality.description.LiteralDescription.COMMA_NEW_LINE;
import static org.saynotobugs.quality.description.LiteralDescription.EMPTY;


@StaticFactories("Core")
public final class ImposesOrderOf<T> implements Quality<Comparator<T>>
{

    private final Iterable<? extends T> mOrderedElements;


    /**
     * Creates a {@link Quality} that matches if the {@link Comparator} under test orders the given elements in the same
     * ascending order (left to right).
     */
    @SafeVarargs
    public ImposesOrderOf(T... orderedElements)
    {
        this(new Seq<>(orderedElements));
    }


    public ImposesOrderOf(Iterable<? extends T> orderedElements)
    {
        mOrderedElements = orderedElements;
    }


    @Override
    public Assessment assessmentOf(Comparator<T> candidate)
    {
        return new AllPassed(new TextDescription("Comparator "), COMMA_NEW_LINE,
            new Mapped<>(
                new UnPaired<Pair<Integer, ? extends T>, Pair<Integer, ? extends T>, Assessment>(
                    (left, right) -> verdict(candidate, left, right)),
                new Expanded<>(
                    element -> new Mapped<>(e -> new ValuePair<>(element, e), new Numbered<>(mOrderedElements)),
                    new Numbered<>(mOrderedElements))));
    }


    @Override
    public Description description()
    {
        return new StructuredDescription(
            new TextDescription("imposes the following order: "),
            COMMA_NEW_LINE,
            EMPTY,
            new Mapped<>(ValueDescription::new, mOrderedElements));
    }


    private Assessment verdict(Comparator<? super T> actual, Pair<Integer, ? extends T> left, Pair<Integer, ? extends T> right)
    {
        return new org.dmfs.jems2.optional.Mapped<>(
            predicate -> testPair(actual, predicate, left, right),
            new org.dmfs.jems2.optional.First<>(predicate -> predicate.satisfiedBy(left.left().compareTo(right.left())),
                new Seq<Predicate<Integer>>(x -> x == 0, x -> x < 0, x -> x > 0))).value();
    }


    private Assessment testPair(Comparator<? super T> actual, Predicate<Integer> comparator, Pair<Integer, ? extends T> left, Pair<Integer, ? extends T> right)
    {
        int result = actual.compare(left.right(), right.right());
        return new PassIf(comparator.satisfiedBy(result), new Delimited(
            new TextDescription("compared elements"),
            new ValueDescription(left.right()),
            new TextDescription("at index " + left.left() + " and"),
            new ValueDescription(right.right()),
            new TextDescription("at index " + right.left() + " incorrectly to"),
            new ValueDescription(result)));
    }
}