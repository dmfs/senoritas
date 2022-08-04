package org.saynotobugs.confidence.quality.trivial;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.assessment.Pass;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.quality.composite.QualityComposition;


@StaticFactories(value = "Core", packageName = "org.saynotobugs.confidence.quality")
public final class Anything extends QualityComposition<Object>
{
    public Anything()
    {
        super(actual -> new Pass(), new TextDescription("<anything>"));
    }
}
