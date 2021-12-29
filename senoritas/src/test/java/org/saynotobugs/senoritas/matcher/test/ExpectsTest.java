package org.saynotobugs.senoritas.matcher.test;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.matcher.core.AllOf;
import org.saynotobugs.senoritas.verdict.Pass;

import java.util.regex.Pattern;

import static org.saynotobugs.senoritas.Assertion.assertThat;


class ExpectsTest
{

    @Test
    void test()
    {
        assertThat(new Expects("abc"),
            new AllOf<>(
                new Matches<>(new Matcher<Object>()
                {
                    @Override
                    public Verdict match(Object actual)
                    {
                        return new Pass();
                    }


                    @Override
                    public Description expectation()
                    {
                        return new TextDescription("abc");
                    }
                }),
                new Mismatches<Matcher<Object>>(new Matcher<Object>()
                {
                    @Override
                    public Verdict match(Object actual)
                    {
                        return new Pass();
                    }


                    @Override
                    public Description expectation()
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
                new Matches<>(new Matcher<Object>()
                {
                    @Override
                    public Verdict match(Object actual)
                    {
                        return new Pass();
                    }


                    @Override
                    public Description expectation()
                    {
                        return new TextDescription("1abc2");
                    }
                }),
                new Mismatches<Matcher<Object>>(new Matcher<Object>()
                {
                    @Override
                    public Verdict match(Object actual)
                    {
                        return new Pass();
                    }


                    @Override
                    public Description expectation()
                    {
                        return new TextDescription("123");
                    }
                }, "expected described as\n  ----\n  \"123\" mismatched pattern <\\\\dabc\\\\d>\n  ----"),
                new Expects("expects describes as\n  ----\n  matches pattern <\\\\dabc\\\\d>\n  ----")
            ));
    }

}