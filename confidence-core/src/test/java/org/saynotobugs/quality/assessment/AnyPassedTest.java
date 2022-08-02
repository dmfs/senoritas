package org.saynotobugs.quality.assessment;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.test.quality.DescribesAs;
import org.saynotobugs.quality.test.quality.Failure;
import org.saynotobugs.quality.test.quality.Passed;

import static org.saynotobugs.quality.Assertion.assertThat;


class AnyPassedTest
{

    @Test
    void testOnePass()
    {
        assertThat(new AnyPassed(new TextDescription("e"), new TextDescription("x"), new Pass()),
            new Passed());
    }


    @Test
    void testMultiplePass()
    {
        assertThat(new AnyPassed(new TextDescription("e"), new TextDescription("x"), new Pass(), new Pass(), new Pass()),
            new Passed());
    }


    @Test
    void testOneFail()
    {
        assertThat(new AnyPassed(new TextDescription("e"), new TextDescription("x"), new Fail(new TextDescription("faaail"))),
            new Failure(new DescribesAs("efaaail")));
    }


    @Test
    void testOneFailOnePass()
    {
        assertThat(new AnyPassed(new TextDescription("e"), new TextDescription("x"), new Fail(new TextDescription("faaail")), new Pass()),
            new Passed());
    }


    @Test
    void testMultipleFail()
    {
        assertThat(new AnyPassed(new TextDescription("e"), new TextDescription("x"), new Fail(new TextDescription("f1")), new Fail(new TextDescription("f2")),
                new Fail(new TextDescription("f3"))),
            new Failure(new DescribesAs("ef1xf2xf3")));
    }


    @Test
    void testMultipleFailWithExitTest()
    {
        assertThat(new AnyPassed(new TextDescription("e"), new TextDescription("x"), new TextDescription("--"), new Fail(new TextDescription("f1")),
                new Fail(new TextDescription("f2")),
                new Fail(new TextDescription("f3"))),
            new Failure(new DescribesAs("ef1xf2xf3--")));
    }
}