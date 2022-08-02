package org.saynotobugs.quality.quality;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.description.Delimited;
import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.description.ValueDescription;
import org.saynotobugs.quality.quality.AllOf;
import org.saynotobugs.quality.quality.Satisfies;
import org.saynotobugs.quality.test.quality.Expects;
import org.saynotobugs.quality.test.quality.Passes;
import org.saynotobugs.quality.test.quality.Fails;

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