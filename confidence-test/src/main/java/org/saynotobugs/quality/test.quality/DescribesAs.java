package org.saynotobugs.quality.test.quality;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.quality.Description;
import org.saynotobugs.quality.Quality;
import org.saynotobugs.quality.Scribe;
import org.saynotobugs.quality.Assessment;
import org.saynotobugs.quality.description.Composite;
import org.saynotobugs.quality.description.DescriptionDescription;
import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.quality.EqualTo;
import org.saynotobugs.quality.quality.MatchesPattern;
import org.saynotobugs.quality.scribe.StringBuilderScribe;
import org.saynotobugs.quality.assessment.FailUpdated;

import java.util.regex.Pattern;


@StaticFactories("Test")
public final class DescribesAs implements Quality<Description>
{
    private final Quality<? super String> mDelegate;


    public DescribesAs(String description)
    {
        this(new EqualTo<>(description));
    }


    public DescribesAs(Pattern description)
    {
        this(new MatchesPattern(description));
    }


    public DescribesAs(Quality<? super String> delegate)
    {
        mDelegate = delegate;
    }


    @Override
    public Assessment assessmentOf(Description candidate)
    {
        Scribe sink = new StringBuilderScribe("  ");
        candidate.describeTo(sink);
        return new FailUpdated(mismatch -> new Composite(new TextDescription("described as"), new DescriptionDescription(mismatch)),
            mDelegate.assessmentOf(sink.toString()));
    }


    @Override
    public Description description()
    {
        return new Composite(new TextDescription("describes as"), new DescriptionDescription(mDelegate.description()));
    }
}
