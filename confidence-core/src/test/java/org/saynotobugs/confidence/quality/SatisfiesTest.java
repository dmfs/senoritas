package org.saynotobugs.confidence.quality;

import org.junit.jupiter.api.Test;
import org.saynotobugs.confidence.description.Delimited;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.description.ValueDescription;
import org.saynotobugs.confidence.test.quality.Expects;
import org.saynotobugs.confidence.test.quality.Passes;
import org.saynotobugs.confidence.test.quality.Fails;

import static org.saynotobugs.confidence.Assertion.assertThat;


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