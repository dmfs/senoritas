package org.saynotobugs.confidence.test.quality;

import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Assessment;
import org.saynotobugs.confidence.Description;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.assessment.AllPassed;
import org.saynotobugs.confidence.assessment.FailUpdated;
import org.saynotobugs.confidence.description.*;

import static org.saynotobugs.confidence.description.LiteralDescription.EMPTY;
import static org.saynotobugs.confidence.description.LiteralDescription.NEW_LINE;


@StaticFactories("Test")
public final class Passes<T> implements Quality<Quality<? super T>>
{
    private final Iterable<? extends T> mMatchingValues;


    @SafeVarargs
    public Passes(T... matchingValues)
    {
        this(new Seq<>(matchingValues));
    }


    public Passes(Iterable<? extends T> matchingValues)
    {
        mMatchingValues = matchingValues;
    }


    @Override
    public Assessment assessmentOf(Quality<? super T> candidate)
    {
        return new AllPassed(new TextDescription("matched"), new Composite(NEW_LINE, new TextDescription("and"), NEW_LINE), EMPTY,
            new Mapped<>(
                value -> new FailUpdated(
                    orig -> new Delimited(new ValueDescription(value), new TextDescription("mismatched with"), new DescriptionDescription(orig)),
                    candidate.assessmentOf(value)),
                mMatchingValues
            ));
    }


    @Override
    public Description description()
    {
        return new StructuredDescription(new TextDescription("matches "), new Composite(NEW_LINE, new TextDescription("and"), NEW_LINE), EMPTY,
            new Mapped<>(ValueDescription::new, mMatchingValues));
    }
}
