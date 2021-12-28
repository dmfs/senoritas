package org.saynotobugs.senoritas.matcher.rxjava3;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.core.AllOf;
import org.saynotobugs.senoritas.matcher.core.Iterates;
import org.saynotobugs.senoritas.matcher.test.Expects;
import org.saynotobugs.senoritas.matcher.test.Matches;
import org.saynotobugs.senoritas.matcher.test.Mismatches;

import io.reactivex.rxjava3.core.Flowable;

import static org.saynotobugs.senoritas.Assertion.assertThat;


class PublisherThatTest
{
    @Test
    void testEmits()
    {
        assertThat(new PublisherThat<>(new Emits<>(3, new Iterates<>(1, 2, 3))),
            new AllOf<>(
                new Matches<>(Flowable.just(1, 2, 3)),
                new Mismatches<>(Flowable.just(1, 2, 2), "Publisher that emitted <3> items that iterated [ ...\n  2: <2> ]"),
                new Expects("Publisher that emits <3> items that iterates [ 0: <1>,\n    1: <2>,\n    2: <3> ]")
            ));
    }


    @Test
    void testEmitsCompletes()
    {
        assertThat(new PublisherThat<>(
                new Emits<>(3, new Iterates<>(1, 2, 3)),
                new HasNoFurtherValues<>(),
                new IsComplete<>()),
            new AllOf<>(
                new Matches<>(Flowable.just(1, 2, 3)),
                new Mismatches<>(Flowable.just(1, 2, 2), "Publisher that emitted <3> items that iterated [ ...\n  2: <2> ]"),
                new Expects(
                    "Publisher that emits <3> items that iterates [ 0: <1>,\n    1: <2>,\n    2: <3> ]\n  and\n  pending emissions has size <0>\n  and\n  completes exactly once")
            ));
    }
}