package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.jems2.Fragile;
import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.test.DescribesAs;
import org.saynotobugs.senoritas.matcher.test.Expects;
import org.saynotobugs.senoritas.matcher.test.Matches;
import org.saynotobugs.senoritas.matcher.test.Mismatches;

import java.util.NoSuchElementException;

import static org.saynotobugs.senoritas.Assertion.assertThat;


class ThrowingTest
{

    @Test
    void test()
    {
        assertThat(new Throwing(new Anything()),
            new AllOf<>(
                new Matches<>((Fragile<Object, NoSuchElementException>) () -> {throw new NoSuchElementException();}),
                new Mismatches<>(() -> "123", new DescribesAs("did not throw <anything>")),
                new Expects(new DescribesAs("throws <anything>"))
            ));
    }


    @Test
    void testNothing()
    {
        assertThat(new Throwing(new Nothing()),
            new AllOf<>(
                new Mismatches<>(() -> {throw new NoSuchElementException();}, new DescribesAs("threw was something")),
                new Mismatches<>(() -> "123", new DescribesAs("did not throw <nothing>")),
                new Expects(new DescribesAs("throws <nothing>"))
            ));
    }
}