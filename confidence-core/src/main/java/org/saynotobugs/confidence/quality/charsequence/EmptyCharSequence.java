package org.saynotobugs.confidence.quality.charsequence;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.description.ValueDescription;
import org.saynotobugs.confidence.quality.composite.QualityComposition;
import org.saynotobugs.confidence.quality.object.Satisfies;


@StaticFactories(value = "Core", packageName = "org.saynotobugs.confidence.quality")
public final class EmptyCharSequence extends QualityComposition<CharSequence>
{
    public EmptyCharSequence()
    {
        super(new Satisfies<>(c -> c.length() == 0, ValueDescription::new, new TextDescription("<empty>")));
    }
}
