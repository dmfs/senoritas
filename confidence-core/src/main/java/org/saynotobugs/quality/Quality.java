package org.saynotobugs.quality;

/**
 * Describes a quality of an object of a certain type.
 */
public interface Quality<T>
{
    Assessment assessmentOf(T candidate);

    Description description();
}
