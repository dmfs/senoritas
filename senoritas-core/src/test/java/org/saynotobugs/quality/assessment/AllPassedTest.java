package org.saynotobugs.quality.assessment;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.quality.Is;
import org.saynotobugs.quality.test.quality.DescribesAs;
import org.saynotobugs.quality.test.quality.Failure;
import org.saynotobugs.quality.test.quality.Passed;

import static org.saynotobugs.quality.Assertion.assertThat;


class AllPassedTest
{
    @Test
    public void testPass()
    {
        assertThat(new AllPassed(new TextDescription("e"), new TextDescription("x"), new Pass(), new Pass()),
            new Passed());
    }


    @Test
    public void testFail()
    {
        assertThat(new AllPassed(new TextDescription("e"), new TextDescription("x"), new Pass(), new Fail(new TextDescription("fail"))),
            new Is<>(new Failure(new DescribesAs("e...\n  fail"))));
    }


    @Test
    public void testMultipleFail()
    {
        assertThat(new AllPassed(new TextDescription("e"), new TextDescription("x"), new Fail(new TextDescription("fail1")), new Pass(),
                new Fail(new TextDescription("fail2")), new Fail(new TextDescription("fail3"))),
            new Is<>(new Failure(new DescribesAs("efail1\n  ...\n  fail2xfail3"))));
    }


    @Test
    public void testMultipleFail2()
    {
        assertThat(
            new AllPassed(new TextDescription("e"), new TextDescription("x"), new TextDescription("<"), new Fail(new TextDescription("fail1")), new Pass(),
                new Fail(new TextDescription("fail2")), new Fail(new TextDescription("fail3"))),
            new Is<>(new Failure(new DescribesAs("efail1\n  ...\n  fail2xfail3<"))));
    }
}