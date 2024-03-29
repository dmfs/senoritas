package org.saynotobugs.confidence;

import org.dmfs.srcless.annotations.composable.Composable;


/**
 * Instances of this type know how to write a description using the given {@link Scribe}.
 */
@Composable(packageName = "org.saynotobugs.confidence.description")
public interface Description
{
    /**
     * Writes this {@link Description} to the given {@link Scribe}.
     */
    void describeTo(Scribe scribe);
}
