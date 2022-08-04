package org.saynotobugs.confidence.test.quality;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Assessment;
import org.saynotobugs.confidence.Description;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.Scribe;
import org.saynotobugs.confidence.assessment.FailUpdated;
import org.saynotobugs.confidence.description.Composite;
import org.saynotobugs.confidence.description.DescriptionDescription;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.quality.object.EqualTo;
import org.saynotobugs.confidence.quality.charsequence.MatchesPattern;
import org.saynotobugs.confidence.scribe.StringBuilderScribe;

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
