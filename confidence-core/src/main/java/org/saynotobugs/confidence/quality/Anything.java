package org.saynotobugs.confidence.quality;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.assessment.Pass;
import org.saynotobugs.confidence.description.TextDescription;


@StaticFactories("Core")
public final class Anything extends QualityComposition<Object>
{
    public Anything()
    {
        super(actual -> new Pass(), new TextDescription("<anything>"));
    }
}
