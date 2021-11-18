package org.saynotobugs.senoritas.matcher.matcher;

import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Scribe;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.description.Composite;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.scribe.StringBuilderScribe;
import org.saynotobugs.senoritas.matcher.core.EqualTo;


public final class DescriptionMatcher implements Matcher<Description>
{
    private final Matcher<String> mDelegate;


    public DescriptionMatcher(String description)
    {
        this(new EqualTo<>(description));
    }


    public DescriptionMatcher(Matcher<String> delegate)
    {
        mDelegate = delegate;
    }


    @Override
    public Verdict match(Description actual)
    {
        Scribe sink = new StringBuilderScribe("  ");
        actual.describeTo(sink);
        return mDelegate.match(sink.toString());
    }


    @Override
    public Description expectation()
    {
        return new Composite(new TextDescription("description"), mDelegate.expectation());
    }
}
