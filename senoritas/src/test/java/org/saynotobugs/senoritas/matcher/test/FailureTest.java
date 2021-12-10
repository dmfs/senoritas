package org.saynotobugs.senoritas.matcher.test;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.core.AllOf;
import org.saynotobugs.senoritas.matcher.core.EqualTo;
import org.saynotobugs.senoritas.verdict.Pass;

import static org.saynotobugs.senoritas.Assertion.assertThat;


class FailureTest
{

    @Test
    void test()
    {
        assertThat(new Failure(new EqualTo<>("<123>")),
            new AllOf<>(
                // currently, we can't match the matching case due to the recursive description
                //    new Matches<>(new Fail(new TextDescription("mismatched with description \n  ----\n  <123>\n  ----"))),
                new Mismatches<>(new Pass(), new DescribesAs("did pass"))));
    }

}