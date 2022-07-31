package org.saynotobugs.quality.test.quality;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.assessment.Pass;
import org.saynotobugs.quality.quality.AllOf;
import org.saynotobugs.quality.quality.EqualTo;

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