package org.saynotobugs.senoritas.matcher.core;

import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.description.Composite;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.description.ValueDescription;


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
                return new Composite(new ValueDescription<>(actual), mDelegate.expectation());
            }

        };
    }


    @Override
    public Description expectation()
    {
        return new Composite(new TextDescription("not"), mDelegate.expectation());
    }
}
