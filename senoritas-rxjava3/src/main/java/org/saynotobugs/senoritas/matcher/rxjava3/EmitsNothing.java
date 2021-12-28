package org.saynotobugs.senoritas.matcher.rxjava3;

import org.dmfs.jems2.Function;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.description.Delimited;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.description.ValueDescription;
import org.saynotobugs.senoritas.matcher.rxjava3.utils.RxTestAdapter;
import org.saynotobugs.senoritas.verdict.PassIf;

import io.reactivex.rxjava3.schedulers.TestScheduler;


@StaticFactories("RxJava3")
public final class EmitsNothing<T> implements Matcher<RxTestAdapter<T>>, Function<TestScheduler, Matcher<RxTestAdapter<T>>>
{

    @Override
    public Verdict match(RxTestAdapter<T> actual)
    {
        return new PassIf(actual.newValues(Integer.MAX_VALUE).size() == 0,
            new Delimited(
                new TextDescription("emitted"),
                new ValueDescription(actual.newValues(Integer.MAX_VALUE))));
    }


    @Override
    public Description expectation()
    {
        return new TextDescription("emits nothing");
    }


    @Override
    public Matcher<RxTestAdapter<T>> value(TestScheduler testScheduler)
    {
        return this;
    }
}
