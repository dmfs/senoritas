package org.saynotobugs.senoritas.matcher.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.test.DescribesAs;
import org.saynotobugs.senoritas.matcher.test.Expects;
import org.saynotobugs.senoritas.matcher.test.Matches;
import org.saynotobugs.senoritas.matcher.test.Mismatches;

import static org.saynotobugs.senoritas.Assertion.assertThat;


class SameAsTest
{

    @Test
    void test()
    {
        Integer i = new Integer(123);
        assertThat(new SameAs<>(i),
            new AllOf<>(
                new Matches<>(i),
                new Mismatches<>(123, new DescribesAs("<123>")),
                new Expects("<123>")));
    }

}