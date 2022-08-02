package org.saynotobugs.confidence.rxjava3;

import org.dmfs.srcless.annotations.composable.Composable;
import org.saynotobugs.confidence.Quality;

import io.reactivex.rxjava3.schedulers.TestScheduler;


/**
 * An expectation on the current state of an {@link RxTestAdapter}.
 */
@FunctionalInterface
@Composable
public interface RxExpectation<T>
{
    /**
     * Returns a {@link Quality} to verify the state of an {@link RxTestAdapter}.
     */
    Quality<RxTestAdapter<T>> quality(TestScheduler scheduler);
}
