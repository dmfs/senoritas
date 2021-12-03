package org.saynotobugs.senoritas.matcher.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.matcher.DescribesAs;
import org.saynotobugs.senoritas.matcher.matcher.Expects;
import org.saynotobugs.senoritas.matcher.matcher.Matches;
import org.saynotobugs.senoritas.matcher.matcher.Mismatches;

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