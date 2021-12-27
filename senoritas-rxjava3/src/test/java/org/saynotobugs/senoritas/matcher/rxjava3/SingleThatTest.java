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
                new Matches<>(Single.just(123)),
                new Mismatches<>(Single.<Integer>error(IOException::new), "Single that emitted <0> items that iterated [ 0: missing <123> ]"),
                new Mismatches<>(Single.just(124), "Single that emitted <1> items that iterated [ 0: <124> ]"),
                new Expects("Single that emits <1> items that iterates [ 0: <123> ]")
            ));
    }
}