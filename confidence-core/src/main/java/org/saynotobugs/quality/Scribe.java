package org.saynotobugs.quality;

public interface Scribe
{
    /**
     * Returns a new {@link Scribe} that adds an indentation to each line.
     */
    Scribe indented();

    /**
     * Writes the given {@link CharSequence} and returns this instance.
     */
    Scribe append(CharSequence charSequence);

    /**
     * Starts a new line with the current indentation and returns this instance.
     */
    Scribe newLine();
}
