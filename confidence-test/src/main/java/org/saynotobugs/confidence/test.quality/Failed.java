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
public final class Failed extends QualityComposition<Assessment>
{
    public Failed(Quality<? super Description> mismatchDescription)
    {
        super(actual -> actual.isSuccess()
                ? new Fail(new TextDescription("passed"))
                : new FailPrepended(new TextDescription("failed with description"), mismatchDescription.assessmentOf(actual.description())),
            new Delimited(new TextDescription("failed with"), mismatchDescription.description()));
    }
}
