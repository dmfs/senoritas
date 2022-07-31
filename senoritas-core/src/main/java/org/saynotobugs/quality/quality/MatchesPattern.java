package org.saynotobugs.quality.quality;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.quality.description.CharSequenceDescription;
import org.saynotobugs.quality.description.Delimited;
import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.description.ValueDescription;

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
