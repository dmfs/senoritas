package org.saynotobugs.confidence.quality;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.description.CharSequenceDescription;
import org.saynotobugs.confidence.description.Delimited;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.description.ValueDescription;

import java.util.regex.Pattern;


@StaticFactories("Core")
public final class MatchesPattern extends QualityComposition<CharSequence>
{
    public MatchesPattern(String pattern)
    {
        this(Pattern.compile(pattern));
    }


    public MatchesPattern(Pattern pattern)
    {
        super(new Satisfies<>(
            actual -> pattern.matcher(actual).matches(),
            actual -> new Delimited(new CharSequenceDescription(actual), new TextDescription("mismatched pattern"), new ValueDescription(pattern)),
            new Delimited(new TextDescription("matches pattern"), new ValueDescription(pattern))));
    }
}
