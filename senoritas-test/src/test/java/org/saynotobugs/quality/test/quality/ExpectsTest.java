package org.saynotobugs.quality.test.quality;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.Assessment;
import org.saynotobugs.quality.Description;
import org.saynotobugs.quality.Quality;
import org.saynotobugs.quality.assessment.Pass;
import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.quality.AllOf;

import java.util.regex.Pattern;

import static org.saynotobugs.quality.Assertion.assertThat;


class ExpectsTest
{

    @Test
    void test()
    {
        assertThat(new Expects("abc"),
            new AllOf<>(
                new Passes<>(new Quality<Object>()
                {
                    @Override
                    public Assessment assessmentOf(Object candidate)
                    {
                        return new Pass();
                    }


                    @Override
                    public Description description()
                    {
                        return new TextDescription("abc");
                    }
                }),
                new Fails<Quality<Object>>(new Quality<Object>()
                {
                    @Override
                    public Assessment assessmentOf(Object candidate)
                    {
                        return new Pass();
                    }


                    @Override
                    public Description description()
                    {
                        return new TextDescription("123");
                    }
                }, "expected described as\n  ----\n  \"123\"\n  ----"),
                new Expects("expects describes as\n  ----\n  \"abc\"\n  ----")
            ));
    }


    @Test
    void testPattern()
    {
        assertThat(new Expects(Pattern.compile("\\dabc\\d")),
            new AllOf<>(
                new Passes<>(new Quality<Object>()
                {
                    @Override
                    public Assessment assessmentOf(Object candidate)
                    {
                        return new Pass();
                    }


                    @Override
                    public Description description()
                    {
                        return new TextDescription("1abc2");
                    }
                }),
                new Fails<Quality<Object>>(new Quality<Object>()
                {
                    @Override
                    public Assessment assessmentOf(Object candidate)
                    {
                        return new Pass();
                    }


                    @Override
                    public Description description()
                    {
                        return new TextDescription("123");
                    }
                }, "expected described as\n  ----\n  \"123\" mismatched pattern <\\\\dabc\\\\d>\n  ----"),
                new Expects("expects describes as\n  ----\n  matches pattern <\\\\dabc\\\\d>\n  ----")
            ));
    }

}