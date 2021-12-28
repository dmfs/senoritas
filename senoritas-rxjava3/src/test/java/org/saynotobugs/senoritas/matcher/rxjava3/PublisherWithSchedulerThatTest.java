package org.saynotobugs.senoritas.matcher.rxjava3;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.core.AllOf;
import org.saynotobugs.senoritas.matcher.test.Expects;
import org.saynotobugs.senoritas.matcher.test.Matches;
import org.saynotobugs.senoritas.matcher.test.Mismatches;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Flowable;

import static org.saynotobugs.senoritas.Assertion.assertThat;


class PublisherWithSchedulerThatTest
{
    @Test
    void testMatch()
    {
        assertThat(new PublisherWithSchedulerThat<>(new After<>(Duration.ofSeconds(2), new Emits<>(1, 2, 3))),
            new AllOf<>(
                new Matches<>(scheduler -> Flowable.just(1, 2, 3).delay(2, TimeUnit.SECONDS, scheduler)),
                new Mismatches<>(scheduler -> Flowable.just(1, 2, 3).delay(30, TimeUnit.SECONDS, scheduler),
                    "Publisher that after PT2S emitted <0> items that iterated [ 0: missing <1>,\n  1: missing <2>,\n  2: missing <3> ]"),
                new Expects("Publisher that after PT2S emits <3> items that iterates [ 0: <1>,\n    1: <2>,\n    2: <3> ]")
            ));
    }
}
