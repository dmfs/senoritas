package org.saynotobugs.quality.quality.test;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.Assessment;
import org.saynotobugs.quality.Description;
import org.saynotobugs.quality.Quality;
import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.quality.core.AllOf;
import org.saynotobugs.quality.assessment.Fail;
import org.saynotobugs.quality.assessment.Pass;

import static org.saynotobugs.quality.Assertion.assertThat;


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