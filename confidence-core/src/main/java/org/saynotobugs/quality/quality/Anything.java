package org.saynotobugs.quality.quality;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.assessment.Pass;


@StaticFactories("Core")
public final class Anything extends QualityComposition<Object>
{
    public Anything()
    {
        super(actual -> new Pass(), new TextDescription("<anything>"));
    }
}
