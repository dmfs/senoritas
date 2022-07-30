package org.saynotobugs.quality.quality.test;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.quality.Assessment;
import org.saynotobugs.quality.description.Delimited;
import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.quality.core.QualityComposition;
import org.saynotobugs.quality.assessment.Fail;
import org.saynotobugs.quality.assessment.FailPrepended;


@StaticFactories("Test")
public final class Passed extends QualityComposition<Assessment>
{

    public Passed()
    {
        super(actual -> actual.isSuccess()
                ? new FailPrepended(new TextDescription("passed but did described mismatch"), new DescribesAs("").assessmentOf(actual.description()))
                : new Fail(new Delimited("failed with", actual.description())),
            new TextDescription("passes"));
    }
}
