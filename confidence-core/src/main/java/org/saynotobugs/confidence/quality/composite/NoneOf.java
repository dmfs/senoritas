package org.saynotobugs.confidence.quality.composite;

import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Assessment;
import org.saynotobugs.confidence.Description;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.assessment.AllPassed;
import org.saynotobugs.confidence.assessment.Fail;
import org.saynotobugs.confidence.assessment.Pass;
import org.saynotobugs.confidence.description.Composite;
import org.saynotobugs.confidence.description.StructuredDescription;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.quality.object.EqualTo;

import static org.saynotobugs.confidence.description.LiteralDescription.EMPTY;
import static org.saynotobugs.confidence.description.LiteralDescription.NEW_LINE;


@StaticFactories(value = "Core", packageName = "org.saynotobugs.confidence.quality")
public final class NoneOf<T> implements Quality<T>
{
    private final Iterable<? extends Quality<? super T>> mDelegates;


    @SafeVarargs
    public NoneOf(T... values)
    {
        this(new Mapped<>(EqualTo::new, new Seq<>(values)));
    }


    @SafeVarargs
    public NoneOf(Quality<? super T>... delegates)
    {
        this(new Seq<>(delegates));
    }


    public NoneOf(Iterable<? extends Quality<? super T>> delegates)
    {
        mDelegates = delegates;
    }


    @Override
    public Assessment assessmentOf(T candidate)
    {
        return new AllPassed(
            new TextDescription("was "), new Composite(new TextDescription(" and"), NEW_LINE), EMPTY,
            new Mapped<>(
                delegate -> {
                    Assessment result = delegate.assessmentOf(candidate);
                    if (result.isSuccess())
                    {
                        return new Fail(delegate.description());
                    }
                    return new Pass();
                },
                mDelegates));
    }


    @Override
    public Description description()
    {
        return new StructuredDescription(new TextDescription("None of "), new Composite(NEW_LINE, new TextDescription("and"), NEW_LINE), EMPTY,
            new Mapped<>(Quality::description, mDelegates));
    }
}
