package org.saynotobugs.confidence.test.quality;

import org.junit.jupiter.api.Test;
import org.saynotobugs.confidence.Assessment;
import org.saynotobugs.confidence.assessment.Fail;
import org.saynotobugs.confidence.assessment.Pass;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.quality.composite.AllOf;
import org.saynotobugs.confidence.quality.grammar.Has;

import static org.saynotobugs.confidence.Assertion.assertThat;


class PassedTest
{

    @Test
    void test()
    {
        assertThat(new Passed(),
            new AllOf<>(
                new Passes<>(new Pass()),
                new Fails<Assessment>(new Assessment()
                {
                    @Override
                    public boolean isSuccess()
                    {
                        return true;
                    }


                    @Override
                    public org.saynotobugs.confidence.Description description()
                    {
                        return new TextDescription("should not exist");
                    }
                }, new DescribesAs("passed but did described mismatch described as\n  ----\n  \"should not exist\"\n  ----")),
                new Fails<>(new Fail(new TextDescription("mismatches")), new DescribesAs("mismatches")),
                new Has<>(new Description("passes"))
            ));
    }

}