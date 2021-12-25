package org.saynotobugs.senoritas.matcher.test;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.matcher.core.AllOf;
import org.saynotobugs.senoritas.verdict.Fail;
import org.saynotobugs.senoritas.verdict.Pass;

import static org.saynotobugs.senoritas.Assertion.assertThat;


class MismatchesTest
{
    @Test
    void test()
    {
        assertThat(new Mismatches<>(123),
            new AllOf<>(
                new Matches<Matcher<Integer>>(new Matcher<Integer>()
                {
                    @Override
                    public Verdict match(Integer actual)
                    {
                        return new Fail(new TextDescription("xyz"));
                    }


                    @Override
                    public Description expectation()
                    {
                        return new TextDescription("expects");
                    }
                }),
                new Mismatches<>(new Matcher<Integer>()
                {
                    @Override
                    public Verdict match(Integer actual)
                    {
                        return new Pass();
                    }


                    @Override
                    public Description expectation()
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
        assertThat(new Mismatches<>(123, "mismatch"),
            new AllOf<>(
                new Matches<Matcher<Integer>>(new Matcher<Integer>()
                {
                    @Override
                    public Verdict match(Integer actual)
                    {
                        return new Fail(new TextDescription("mismatch"));
                    }


                    @Override
                    public Description expectation()
                    {
                        return new TextDescription("expects");
                    }
                }),
                new Mismatches<>(new Matcher<Integer>()
                {
                    @Override
                    public Verdict match(Integer actual)
                    {
                        return new Fail(new TextDescription("abc"));
                    }


                    @Override
                    public Description expectation()
                    {
                        return new TextDescription("pass");
                    }
                }, "<123> mismatched with diff described as\n  ----\n  \"abc\"\n  ----"),
                new Mismatches<>(new Matcher<Integer>()
                {
                    @Override
                    public Verdict match(Integer actual)
                    {
                        return new Pass();
                    }


                    @Override
                    public Description expectation()
                    {
                        return new TextDescription("pass");
                    }
                }, "<123> matched pass"),
                new Expects("mismatches <123> with diff describes as\n  ----\n  \"mismatch\"\n  ----")
            ));
    }
}