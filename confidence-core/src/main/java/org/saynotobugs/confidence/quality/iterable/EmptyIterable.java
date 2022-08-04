package org.saynotobugs.confidence.quality.iterable;

import org.dmfs.jems2.predicate.Not;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.description.ValueDescription;
import org.saynotobugs.confidence.quality.composite.QualityComposition;
import org.saynotobugs.confidence.quality.object.Satisfies;


@StaticFactories(value = "Core", packageName = "org.saynotobugs.confidence.quality")
public final class EmptyIterable extends QualityComposition<Iterable<?>>
{
    public EmptyIterable()
    {
        super(new Satisfies<>(new Not<>(actual -> actual.iterator().hasNext()), ValueDescription::new, new TextDescription("<empty>")));
    }
}
