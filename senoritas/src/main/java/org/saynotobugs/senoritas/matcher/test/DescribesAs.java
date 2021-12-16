package org.saynotobugs.senoritas.matcher.test;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.Scribe;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.description.Composite;
import org.saynotobugs.senoritas.description.DescriptionDescription;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.matcher.core.EqualTo;
import org.saynotobugs.senoritas.scribe.StringBuilderScribe;
import org.saynotobugs.senoritas.verdict.MismatchUpdated;


@StaticFactories("Test")
public final class DescribesAs implements Matcher<Description>
{
    private final Matcher<String> mDelegate;


    public DescribesAs(String description)
    {
        this(new EqualTo<>(description));
    }


    public DescribesAs(Matcher<String> delegate)
    {
        mDelegate = delegate;
    }


    @Override
    public Verdict match(Description actual)
    {
        Scribe sink = new StringBuilderScribe("  ");
        actual.describeTo(sink);
        return new MismatchUpdated(mismatch -> new Composite(new TextDescription("described as"), new DescriptionDescription(mismatch)),
            mDelegate.match(sink.toString()));
    }


    @Override
    public Description expectation()
    {
        return new Composite(new TextDescription("describes as"), new DescriptionDescription(mDelegate.expectation()));
    }
}
