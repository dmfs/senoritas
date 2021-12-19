package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.description.Delimited;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.description.ValueDescription;


@StaticFactories("Core")
public final class Not<T> implements Matcher<T>
{
    private final Matcher<? super T> mDelegate;


    public Not(T delegate)
    {
        this(new EqualTo<>(delegate));
    }


    public Not(Matcher<? super T> delegate)
    {
        this.mDelegate = delegate;
    }


    @Override
    public Verdict match(T actual)
    {
        Verdict result = mDelegate.match(actual);
        return new Verdict()
        {
            @Override
            public boolean isSuccess()
            {
                return !result.isSuccess();
            }


            @Override
            public Description description()
            {
                return new Delimited(new ValueDescription(actual), new TextDescription("("), mDelegate.expectation(), new TextDescription(")"));
            }
        };
    }


    @Override
    public Description expectation()
    {
        return new Delimited(new TextDescription("not ("), mDelegate.expectation(), new TextDescription(")"));
    }
}
