package org.saynotobugs.quality.quality.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.description.Delimited;
import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.description.ValueDescription;
import org.saynotobugs.quality.quality.test.Expects;
import org.saynotobugs.quality.quality.test.Passes;
import org.saynotobugs.quality.quality.test.Fails;

import static org.saynotobugs.quality.Assertion.assertThat;


class SatisfiesTest
{

    @Test
    void test()
    {
        assertThat(new Satisfies<>(String::isEmpty,
                s -> new Delimited(new ValueDescription(s), new TextDescription("was not empty")), new TextDescription("is empty")),
            new AllOf<>(
                new Passes<>(""),
                new Fails<>("abc", "\"abc\" was not empty"),
                new Expects("is empty")
            ));
    }

}