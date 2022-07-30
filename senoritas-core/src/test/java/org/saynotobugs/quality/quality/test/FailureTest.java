package org.saynotobugs.quality.quality.test;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.quality.core.AllOf;
import org.saynotobugs.quality.quality.core.EqualTo;
import org.saynotobugs.quality.assessment.Pass;

import static org.saynotobugs.quality.Assertion.assertThat;


class FailureTest
{

    @Test
    void test()
    {
        assertThat(new Failure(new EqualTo<>("<123>")),
            new AllOf<>(
                // currently, we can't match the matching case due to the recursive description
                //    new Matches<>(new Fail(new TextDescription("mismatched with description \n  ----\n  <123>\n  ----"))),
                new Fails<>(new Pass(), new DescribesAs("did pass"))));
    }

}