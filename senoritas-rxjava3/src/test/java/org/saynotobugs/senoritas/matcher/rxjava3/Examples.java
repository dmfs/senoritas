package org.saynotobugs.senoritas.matcher.rxjava3;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.description.TextDescription;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.schedulers.TestScheduler;

import static java.time.Duration.ofSeconds;
import static org.saynotobugs.senoritas.Assertion.assertThat;
import static org.saynotobugs.senoritas.matcher.core.Core.is;
import static org.saynotobugs.senoritas.matcher.rxjava3.RxJava3.*;


public final class Examples
{
    @Test
    void testInterval()
    {
        assertThat((TestScheduler scheduler) -> Flowable.interval(10, TimeUnit.SECONDS, scheduler).take(10),
            is(publisherThat(
                immediately(emitsNothing()),
                within(ofSeconds(10), emits(0L)),
                within(ofSeconds(10), emits(1L)),
                within(ofSeconds(10), emits(2L)),
                within(ofSeconds(10), emits(3L)),
                within(ofSeconds(10), emits(4L)),
                within(ofSeconds(10), emits(5L)),
                within(ofSeconds(10), emits(6L)),
                within(ofSeconds(10), emits(7L)),
                within(ofSeconds(10), emits(8L)),
                within(ofSeconds(10), emits(9L)),
                immediately(completes())
            )));
    }


    @Test
    void testTrigger()
    {
        AtomicInteger value = new AtomicInteger(5);
        assertThat((TestScheduler scheduler) -> Flowable.interval(10, TimeUnit.SECONDS, scheduler).map(i -> value.get()).take(10),
            is(publisherThat(
                immediately(emitsNothing()),
                within(ofSeconds(10), emits(5)),
                within(ofSeconds(10), emits(5)),
                within(ofSeconds(10), emits(5)),
                within(ofSeconds(10), emits(5)),
                when(new TextDescription("Value set to 10"), () -> value.set(10), within(ofSeconds(10), emits(10))),
                within(ofSeconds(10), emits(10)),
                within(ofSeconds(10), emits(10)),
                within(ofSeconds(10), emits(10)),
                within(ofSeconds(10), emits(10)),
                within(ofSeconds(10), emits(10)),
                immediately(completes())
            )));
    }


    @Test
    void testMaybeError()
    {
        assertThat((TestScheduler scheduler) -> Maybe.error(IOException::new).delay(10, TimeUnit.SECONDS, scheduler),
            is(maybeThat(immediately(errors(IOException.class)))));
    }


    @Test
    void testMaybeDelayedError()
    {
        assertThat((TestScheduler scheduler) -> Maybe.empty().delay(10, TimeUnit.SECONDS, scheduler).switchIfEmpty(Maybe.error(IOException::new)),
            is(maybeThat(
                immediately(isAlive()),
                within(ofSeconds(10), errors(IOException.class)))));
    }
}
