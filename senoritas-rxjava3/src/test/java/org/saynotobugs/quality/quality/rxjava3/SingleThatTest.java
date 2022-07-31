package org.saynotobugs.quality.quality.rxjava3;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.quality.AllOf;
import org.saynotobugs.quality.quality.EqualTo;
import org.saynotobugs.quality.test.quality.Expects;
import org.saynotobugs.quality.test.quality.Fails;
import org.saynotobugs.quality.test.quality.Passes;

import java.io.IOException;

import io.reactivex.rxjava3.core.Single;

import static org.saynotobugs.quality.Assertion.assertThat;


class SingleThatTest
{
    @Test
    void testEmits()
    {
        assertThat(new SingleThat<>(new Emits<>(new EqualTo<>(123))),
            new AllOf<>(
                new Passes<>(ignored -> Single.just(123)),
                new Fails<>(ignored -> Single.error(IOException::new), "Single that (0) emitted <0> items that iterated [ 0: missing <123> ]"),
                new Fails<>(ignored -> Single.just(124), "Single that (0) emitted <1> items that iterated [ 0: <124> ]"),
                new Expects("Single that (0) emits <1> items that iterates [ 0: <123> ]")
            ));
    }
}