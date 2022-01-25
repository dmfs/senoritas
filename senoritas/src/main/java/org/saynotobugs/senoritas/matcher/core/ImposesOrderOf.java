package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.jems2.Pair;
import org.dmfs.jems2.Predicate;
import org.dmfs.jems2.iterable.Expanded;
import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.Numbered;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.jems2.pair.ValuePair;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.description.Delimited;
import org.saynotobugs.senoritas.description.StructuredDescription;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.description.ValueDescription;
import org.saynotobugs.senoritas.utils.UnPaired;
import org.saynotobugs.senoritas.verdict.AllPassed;
import org.saynotobugs.senoritas.verdict.PassIf;

import java.util.Comparator;

import static org.saynotobugs.senoritas.description.LiteralDescription.COMMA_NEW_LINE;
import static org.saynotobugs.senoritas.description.LiteralDescription.EMPTY;


@StaticFactories("Core")
public final class ImposesOrderOf<T> implements Matcher<Comparator<T>>
{

    private final Iterable<? extends T> mOrderedElements;


    /**
     * Creates a {@link Matcher} that matches if the {@link Comparator} under test orders the given elements in the same
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
    public Verdict match(Comparator<T> actual)
    {
        return new AllPassed(new TextDescription("Comparator "), COMMA_NEW_LINE,
            new Mapped<>(
                new UnPaired<Pair<Integer, ? extends T>, Pair<Integer, ? extends T>, Verdict>(
                    (left, right) -> verdict(actual, left, right)),
                new Expanded<>(
                    element -> new Mapped<>(e -> new ValuePair<>(element, e), new Numbered<>(mOrderedElements)),
                    new Numbered<>(mOrderedElements))));
    }


    @Override
    public Description expectation()
    {
        return new StructuredDescription(
            new TextDescription("imposes the following order: "),
            COMMA_NEW_LINE,
            EMPTY,
            new Mapped<>(ValueDescription::new, mOrderedElements));
    }


    private Verdict verdict(Comparator<? super T> actual, Pair<Integer, ? extends T> left, Pair<Integer, ? extends T> right)
    {
        return new org.dmfs.jems2.optional.Mapped<>(
            predicate -> testPair(actual, predicate, left, right),
            new org.dmfs.jems2.optional.First<>(predicate -> predicate.satisfiedBy(left.left().compareTo(right.left())),
                new Seq<Predicate<Integer>>(x -> x == 0, x -> x < 0, x -> x > 0))).value();
    }


    private Verdict testPair(Comparator<? super T> actual, Predicate<Integer> comparator, Pair<Integer, ? extends T> left, Pair<Integer, ? extends T> right)
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
