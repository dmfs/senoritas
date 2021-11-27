package org.saynotobugs.senoritas;

/**
 * Instances of this type know how to write a description using the given {@link Scribe}.
 */
public interface Description
{
    /**
     * Writes this {@link Description} to the given {@link Scribe}.
     */
    void describeTo(Scribe scribe);
}
