package org.saynotobugs.confidence.test.quality;

import org.junit.jupiter.api.Test;
import org.saynotobugs.confidence.Assessment;
import org.saynotobugs.confidence.Description;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.assessment.Pass;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.quality.composite.AllOf;

import java.util.regex.Pattern;

import static org.saynotobugs.confidence.Assertion.assertThat;


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