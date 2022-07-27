package org.saynotobugs.quality.quality.test;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.Assessment;
import org.saynotobugs.quality.Description;
import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.quality.core.AllOf;
import org.saynotobugs.quality.assessment.Fail;
import org.saynotobugs.quality.assessment.Pass;

import static org.saynotobugs.quality.Assertion.assertThat;


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
                    public Description description()
                    {
                        return new TextDescription("should not exist");
                    }
                }, new DescribesAs("passed but did described mismatch described as\n  ----\n  \"should not exist\"\n  ----")),
                new Fails<>(new Fail(new TextDescription("mismatches")), new DescribesAs("mismatches")),
                new Expects("passes")
            ));
    }

}