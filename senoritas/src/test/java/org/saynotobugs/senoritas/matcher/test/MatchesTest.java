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


class MatchesTest
{

    @Test
    void test()
    {
        assertThat(new Matches<>(1, 2, 3),
            new AllOf<>(
                new Matches<Matcher<Integer>>(new Matcher<Integer>()
                {
                    @Override
                    public Verdict match(Integer actual)
                    {
                        return new Pass();
                    }


                    @Override
                    public Description expectation()
                    {
                        return new TextDescription("passes");
                    }
                }),
                new Mismatches<>(new Matcher<Integer>()
                {
                    @Override
                    public Verdict match(Integer actual)
                    {
                        return new Fail(new TextDescription("failed"));
                    }


                    @Override
                    public Description expectation()
                    {
                        return new TextDescription("passes");
                    }
                },
                    "matched<1> mismatched with \n    ----\n    failed\n    ----\n  and\n  <2> mismatched with \n    ----\n    failed\n    ----\n  and\n  <3> mismatched with \n    ----\n    failed\n    ----"),
                new Expects("matches<1>\n  and\n  <2>\n  and\n  <3>")
            ));
    }

}