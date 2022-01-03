package org.saynotobugs.senoritas.matcher.rxjava3;

import org.dmfs.srcless.annotations.composable.Composable;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.matcher.rxjava3.adapters.RxTestAdapter;

import io.reactivex.rxjava3.schedulers.TestScheduler;


/**
 * An expectation on the current state of an {@link RxTestAdapter}.
 */
@FunctionalInterface
@Composable
public interface RxExpectation<T>
{
    /**
     * Returns a {@link Matcher} to verify the state of an {@link RxTestAdapter}.
     */
    Matcher<RxTestAdapter<T>> matcher(TestScheduler scheduler);
}
