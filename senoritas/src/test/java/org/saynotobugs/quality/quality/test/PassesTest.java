package org.saynotobugs.quality.quality.test;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.Description;
import org.saynotobugs.quality.Quality;
import org.saynotobugs.quality.Assessment;
import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.quality.core.AllOf;
import org.saynotobugs.quality.assessment.Fail;
import org.saynotobugs.quality.assessment.Pass;

import static org.saynotobugs.quality.Assertion.assertThat;


class PassesTest
{

    @Test
    void test()
    {
        assertThat(new Passes<>(1, 2, 3),
            new AllOf<>(
                new Passes<Quality<Integer>>(new Quality<Integer>()
                {
                    @Override
                    public Assessment assessmentOf(Integer candidate)
                    {
                        return new Pass();
                    }


                    @Override
                    public Description description()
                    {
                        return new TextDescription("passes");
                    }
                }),
                new Fails<>(new Quality<Integer>()
                {
                    @Override
                    public Assessment assessmentOf(Integer candidate)
                    {
                        return new Fail(new TextDescription("failed"));
                    }


                    @Override
                    public Description description()
                    {
                        return new TextDescription("passes");
                    }
                },
                    "matched<1> mismatched with \n    ----\n    failed\n    ----\n  and\n  <2> mismatched with \n    ----\n    failed\n    ----\n  and\n  <3> mismatched with \n    ----\n    failed\n    ----"),
                new Expects("matches<1>\n  and\n  <2>\n  and\n  <3>")
            ));
    }

}