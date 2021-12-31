package org.saynotobugs.senoritas.matcher.rxjava3;

import org.dmfs.srcless.annotations.composable.Composable;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.matcher.rxjava3.utils.RxTestAdapter;

import io.reactivex.rxjava3.schedulers.TestScheduler;


@FunctionalInterface
@Composable
public interface TestEvent<T>
{
    Matcher<RxTestAdapter<T>> matcher(TestScheduler scheduler);
}
