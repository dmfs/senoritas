package org.saynotobugs.senoritas.matcher.rxjava3;

import org.dmfs.jems2.Function;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.matcher.core.Having;
import org.saynotobugs.senoritas.matcher.core.MatcherComposition;

import io.reactivex.rxjava3.schedulers.TestScheduler;


@StaticFactories("RxJava3")
public final class Unscheduled<RxType> extends MatcherComposition<RxType>
{
    /**
     * Creates a {@link Matcher} to test RX type implementations that don't require a scheduler.
     * <p>
     * Scheduler events like {@link Within} will be ignored.
     */
    public Unscheduled(Matcher<? super Function<? super TestScheduler, ? extends RxType>> delegate)
    {
        super(new Having<>(
            matcher -> (Function<? super TestScheduler, ? extends RxType>) ignoredScheduler -> matcher,
            delegate));
    }
}
