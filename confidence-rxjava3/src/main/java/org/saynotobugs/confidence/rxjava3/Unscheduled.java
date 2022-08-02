package org.saynotobugs.confidence.rxjava3;

import org.dmfs.jems2.Function;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.quality.Having;
import org.saynotobugs.confidence.quality.QualityComposition;

import io.reactivex.rxjava3.schedulers.TestScheduler;


@StaticFactories("RxJava3")
public final class Unscheduled<RxType> extends QualityComposition<RxType>
{
    /**
     * Creates a {@link Quality} to test RX type implementations that don't require a scheduler.
     * <p>
     * Scheduler events like {@link Within} will be ignored.
     */
    public Unscheduled(Quality<? super Function<? super TestScheduler, ? extends RxType>> delegate)
    {
        super(new Having<>(
            matcher -> (Function<? super TestScheduler, ? extends RxType>) ignoredScheduler -> matcher,
            delegate));
    }
}
