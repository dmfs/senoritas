package org.saynotobugs.senoritas.matcher.rxjava3;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.core.AllOf;
import org.saynotobugs.senoritas.matcher.core.EqualTo;
import org.saynotobugs.senoritas.matcher.test.Expects;
import org.saynotobugs.senoritas.matcher.test.Matches;
import org.saynotobugs.senoritas.matcher.test.Mismatches;

import java.io.IOException;

import io.reactivex.rxjava3.core.Single;

import static org.saynotobugs.senoritas.Assertion.assertThat;


class SingleThatTest
{
    @Test
    void testEmits()
    {
        assertThat(new SingleThat<>(new Emits<>(new EqualTo<>(123))),
            new AllOf<>(
                new Matches<>(ignored -> Single.just(123)),
                new Mismatches<>(ignored -> Single.error(IOException::new), "Single that (0) emitted <0> items that iterated [ 0: missing <123> ]"),
                new Mismatches<>(ignored -> Single.just(124), "Single that (0) emitted <1> items that iterated [ 0: <124> ]"),
                new Expects("Single that (0) emits <1> items that iterates [ 0: <123> ]")
            ));
    }
}