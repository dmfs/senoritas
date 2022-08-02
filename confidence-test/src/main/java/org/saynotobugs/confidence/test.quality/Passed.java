package org.saynotobugs.confidence.test.quality;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Assessment;
import org.saynotobugs.confidence.description.Delimited;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.quality.QualityComposition;
import org.saynotobugs.confidence.assessment.Fail;
import org.saynotobugs.confidence.assessment.FailPrepended;


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
