package org.saynotobugs.quality.test.quality;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.quality.Assessment;
import org.saynotobugs.quality.Description;
import org.saynotobugs.quality.Quality;
import org.saynotobugs.quality.description.Delimited;
import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.quality.QualityComposition;
import org.saynotobugs.quality.assessment.Fail;
import org.saynotobugs.quality.assessment.FailPrepended;


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
