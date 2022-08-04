package org.saynotobugs.confidence.test.quality;

import org.junit.jupiter.api.Test;
import org.saynotobugs.confidence.Assessment;
import org.saynotobugs.confidence.Description;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.assessment.Fail;
import org.saynotobugs.confidence.assessment.Pass;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.quality.composite.AllOf;

import static org.saynotobugs.confidence.Assertion.assertThat;


class FailsTest
{
    @Test
    void test()
    {
        assertThat(new Fails<>(123),
            new AllOf<>(
                new Passes<Quality<Integer>>(new Quality<Integer>()
                {
                    @Override
                    public Assessment assessmentOf(Integer candidate)
                    {
                        return new Fail(new TextDescription("xyz"));
                    }


                    @Override
                    public Description description()
                    {
                        return new TextDescription("expects");
                    }
                }),
                new Fails<>(new Quality<Integer>()
                {
                    @Override
                    public Assessment assessmentOf(Integer candidate)
                    {
                        return new Pass();
                    }


                    @Override
                    public Description description()
                    {
                        return new TextDescription("pass");
                    }
                }, "<123> matched pass"),
                new Expects("mismatches <123> with diff <anything>")
            ));
    }


    @Test
    void testWithDescription()
    {
        assertThat(new Fails<>(123, "mismatch"),
            new AllOf<>(
                new Passes<Quality<Integer>>(new Quality<Integer>()
                {
                    @Override
                    public Assessment assessmentOf(Integer candidate)
                    {
                        return new Fail(new TextDescription("mismatch"));
                    }


                    @Override
                    public Description description()
                    {
                        return new TextDescription("expects");
                    }
                }),
                new Fails<>(new Quality<Integer>()
                {
                    @Override
                    public Assessment assessmentOf(Integer candidate)
                    {
                        return new Fail(new TextDescription("abc"));
                    }


                    @Override
                    public Description description()
                    {
                        return new TextDescription("pass");
                    }
                }, "<123> mismatched with diff described as\n  ----\n  \"abc\"\n  ----"),
                new Fails<>(new Quality<Integer>()
                {
                    @Override
                    public Assessment assessmentOf(Integer candidate)
                    {
                        return new Pass();
                    }


                    @Override
                    public Description description()
                    {
                        return new TextDescription("pass");
                    }
                }, "<123> matched pass"),
                new Expects("mismatches <123> with diff describes as\n  ----\n  \"mismatch\"\n  ----")
            ));
    }
}