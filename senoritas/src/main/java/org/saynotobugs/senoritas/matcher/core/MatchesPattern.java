package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.description.CharSequenceDescription;
import org.saynotobugs.senoritas.description.Delimited;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.description.ValueDescription;
import org.saynotobugs.senoritas.matcher.MatcherComposition;

import java.util.regex.Pattern;


@StaticFactories("Core")
public final class MatchesPattern extends MatcherComposition<CharSequence>
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
