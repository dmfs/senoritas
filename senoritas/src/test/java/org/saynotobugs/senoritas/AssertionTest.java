package org.saynotobugs.senoritas;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.core.Is;
import org.saynotobugs.senoritas.matcher.core.Nothing;
import org.saynotobugs.senoritas.matcher.core.Throwing;

import static org.saynotobugs.senoritas.Assertion.assertThat;


class AssertionTest
{

    @Test
    void test()
    {
        assertThat(() -> {
                assertThat("123", new Nothing());
                // I guess throwing needs a dedicated interface to test
                return this;
            },
            new Is<>(new Throwing(AssertionError.class)));
    }

}