package org.saynotobugs.quality.quality.core;

import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.quality.Description;
import org.saynotobugs.quality.Quality;
import org.saynotobugs.quality.Assessment;
import org.saynotobugs.quality.description.Composite;
import org.saynotobugs.quality.description.StructuredDescription;
import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.assessment.AllPassed;
import org.saynotobugs.quality.assessment.Fail;
import org.saynotobugs.quality.assessment.Pass;

import static org.saynotobugs.quality.description.LiteralDescription.EMPTY;
import static org.saynotobugs.quality.description.LiteralDescription.NEW_LINE;


@StaticFactories("Core")
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
