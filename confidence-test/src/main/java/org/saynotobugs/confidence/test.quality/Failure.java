package org.saynotobugs.confidence.test.quality;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Assessment;
import org.saynotobugs.confidence.Description;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.assessment.Fail;
import org.saynotobugs.confidence.assessment.FailPrepended;
import org.saynotobugs.confidence.description.Delimited;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.quality.composite.QualityComposition;


@StaticFactories("Test")
public final class Failure extends QualityComposition<Assessment>
{
    public Failure(Quality<? super Description> mismatchDescription)
    {
        super(actual -> actual.isSuccess()
                ? new Fail(new TextDescription("did pass"))
                : new FailPrepended(new TextDescription("mismatched with description"), mismatchDescription.assessmentOf(actual.description())),
            new Delimited(new TextDescription("failed with"), mismatchDescription.description()));
    }
}
