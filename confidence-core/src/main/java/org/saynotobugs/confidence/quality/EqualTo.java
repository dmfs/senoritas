package org.saynotobugs.confidence.quality;

import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Assessment;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.assessment.FailPrepended;
import org.saynotobugs.confidence.assessment.PassIf;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.description.ValueDescription;
import org.saynotobugs.confidence.utils.ArrayIterable;


@StaticFactories("Core")
public final class EqualTo<T> extends QualityComposition<T>
{
    /**
     * Creates a {@link Quality} that matches if the value under test is equal to the given value.
     */
    public EqualTo(T expected)
    {
        super(actual -> attestation(expected, actual),
            new ValueDescription(expected));
    }


    private static <T> Assessment attestation(T expected, T actual)
    {
        return expected.getClass().isArray() && actual.getClass().isArray()
            ? new FailPrepended(new TextDescription("array that"),
            new Iterates<>(new Mapped<>(EqualTo::new, new ArrayIterable(expected))).assessmentOf(new ArrayIterable(actual)))
            : new PassIf(expected.equals(actual), new ValueDescription(actual));
    }

}
