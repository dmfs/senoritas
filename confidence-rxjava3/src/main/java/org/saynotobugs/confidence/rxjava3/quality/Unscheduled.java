package org.saynotobugs.confidence.rxjava3.quality;

import org.dmfs.jems2.Function;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.quality.composite.QualityComposition;
import org.saynotobugs.confidence.quality.composite.Having;
import org.saynotobugs.confidence.rxjava3.rxexpectation.Within;

import io.reactivex.rxjava3.schedulers.TestScheduler;


@StaticFactories(value = "RxJava3", packageName = "org.saynotobugs.confidence.rxjava3")
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
