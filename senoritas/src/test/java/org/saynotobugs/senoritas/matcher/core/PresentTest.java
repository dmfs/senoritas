package org.saynotobugs.senoritas.matcher.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.test.DescribesAs;
import org.saynotobugs.senoritas.matcher.test.Expects;
import org.saynotobugs.senoritas.matcher.test.Matches;
import org.saynotobugs.senoritas.matcher.test.Mismatches;

import java.util.Optional;

import static org.saynotobugs.senoritas.Assertion.assertThat;


class PresentTest
{

    @Test
    void testDefault()
    {
        assertThat(new Present<>(),
            new AllOf<>(
                new Matches<>(Optional.of(123)),
                new Matches<Optional<Object>>(Optional.of(1234)),
                new Matches<Optional<Object>>(Optional.of("abc")),
                new Mismatches<Optional<Object>>(Optional.empty(), new DescribesAs("was absent")),
                new Expects("is present <anything>")));
    }


    @Test
    void testWithValue()
    {
        assertThat(new Present<>(123),
            new AllOf<>(
                new Matches<>(Optional.of(123)),
                new Mismatches<>(Optional.of(1234), new DescribesAs("was present <1234>")),
                new Mismatches<Optional<Integer>>(Optional.empty(), new DescribesAs("was absent")),
                new Expects("is present <123>")));
    }


    @Test
    void testWithMatcher()
    {
        assertThat(new Present<>(new EqualTo<>(123)),
            new AllOf<>(
                new Matches<>(Optional.of(123)),
                new Mismatches<>(Optional.of(1234), new DescribesAs("was present <1234>")),
                new Mismatches<Optional<Integer>>(Optional.empty(), new DescribesAs("was absent")),
                new Expects("is present <123>")));
    }

}