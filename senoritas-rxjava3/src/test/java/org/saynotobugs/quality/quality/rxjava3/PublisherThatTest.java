package org.saynotobugs.quality.quality.rxjava3;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.quality.AllOf;
import org.saynotobugs.quality.test.quality.Expects;
import org.saynotobugs.quality.test.quality.Fails;
import org.saynotobugs.quality.test.quality.Passes;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Flowable;

import static org.saynotobugs.quality.Assertion.assertThat;


class PublisherThatTest
{
    @Test
    void testMatch()
    {
        assertThat(new PublisherThat<>(new Within<>(Duration.ofSeconds(2), new Emits<>(1, 2, 3))),
            new AllOf<>(
                new Passes<>(scheduler -> Flowable.just(1, 2, 3).delay(2, TimeUnit.SECONDS, scheduler)),
                new Fails<>(scheduler -> Flowable.just(1, 2, 3).delay(30, TimeUnit.SECONDS, scheduler),
                    "Publisher that (0) after PT2S emitted <0> items that iterated [ 0: missing <1>,\n  1: missing <2>,\n  2: missing <3> ]"),
                new Expects("Publisher that (0) after PT2S emits <3> items that iterates [ 0: <1>,\n    1: <2>,\n    2: <3> ]")
            ));
    }
}
