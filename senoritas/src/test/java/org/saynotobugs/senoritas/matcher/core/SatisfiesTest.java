package org.saynotobugs.senoritas.matcher.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.description.Delimited;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.description.ValueDescription;
import org.saynotobugs.senoritas.matcher.test.Expects;
import org.saynotobugs.senoritas.matcher.test.Matches;
import org.saynotobugs.senoritas.matcher.test.Mismatches;

import static org.saynotobugs.senoritas.Assertion.assertThat;


class SatisfiesTest
{

    @Test
    void test()
    {
        assertThat(new Satisfies<>(String::isEmpty,
                s -> new Delimited(new ValueDescription(s), new TextDescription("was not empty")), new TextDescription("is empty")),
            new AllOf<>(
                new Matches<>(""),
                new Mismatches<>("abc", "\"abc\" was not empty"),
                new Expects("is empty")
            ));
    }

}