package org.saynotobugs.confidence.quality.charsequence;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.description.CharSequenceDescription;
import org.saynotobugs.confidence.description.Delimited;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.description.ValueDescription;
import org.saynotobugs.confidence.quality.composite.QualityComposition;
import org.saynotobugs.confidence.quality.object.Satisfies;

import java.util.regex.Pattern;


@StaticFactories(value = "Core", packageName = "org.saynotobugs.confidence.quality")
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
