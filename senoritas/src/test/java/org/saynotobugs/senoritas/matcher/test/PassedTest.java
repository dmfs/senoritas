package org.saynotobugs.senoritas.matcher.test;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.matcher.core.AllOf;
import org.saynotobugs.senoritas.verdict.Fail;
import org.saynotobugs.senoritas.verdict.Pass;

import static org.saynotobugs.senoritas.Assertion.assertThat;


class PassedTest
{

    @Test
    void test()
    {
        assertThat(new Passed(),
            new AllOf<>(
                new Matches<>(new Pass()),
                new Mismatches<Verdict>(new Verdict()
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
                new Mismatches<>(new Fail(new TextDescription("mismatches")), new DescribesAs("mismatches")),
                new Expects("passes")
            ));
    }

}