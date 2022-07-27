package org.saynotobugs.quality.quality.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.quality.test.DescribesAs;
import org.saynotobugs.quality.quality.test.Expects;
import org.saynotobugs.quality.quality.test.Fails;
import org.saynotobugs.quality.quality.test.Passes;

import java.util.Optional;

import static org.saynotobugs.quality.Assertion.assertThat;


class PresentTest
{

    @Test
    void testDefault()
    {
        assertThat(new Present<>(),
            new AllOf<>(
                new Passes<Optional<Object>>(Optional.of(123), Optional.of(1234), Optional.of("abc")),
                new Fails<>(Optional.empty(), new DescribesAs("was absent")),
                new Expects("is present <anything>")));
    }


    @Test
    void testWithValue()
    {
        assertThat(new Present<>(123),
            new AllOf<>(
                new Passes<>(Optional.of(123)),
                new Fails<>(Optional.of(1234), new DescribesAs("was present <1234>")),
                new Fails<Optional<Integer>>(Optional.empty(), new DescribesAs("was absent")),
                new Expects("is present <123>")));
    }


    @Test
    void testWithMatcher()
    {
        assertThat(new Present<>(new EqualTo<>(123)),
            new AllOf<>(
                new Passes<>(Optional.of(123)),
                new Fails<>(Optional.of(1234), new DescribesAs("was present <1234>")),
                new Fails<Optional<Integer>>(Optional.empty(), new DescribesAs("was absent")),
                new Expects("is present <123>")));
    }

}