package org.saynotobugs.quality.scribe;

import org.saynotobugs.quality.Scribe;


public final class StringBuilderScribe implements Scribe
{
    private final StringBuilder mStringBuilder;
    private final String mCurrentLinePrefix;
    private final String mIndent;


    public StringBuilderScribe(String indent)
    {
        this(new StringBuilder(), "", indent);
    }


    private StringBuilderScribe(StringBuilder stringBuilder, String currentLinePrefix, String indent)
    {
        mStringBuilder = stringBuilder;
        mCurrentLinePrefix = currentLinePrefix;
        mIndent = indent;
    }


    @Override
    public Scribe indented()
    {
        return new StringBuilderScribe(mStringBuilder, mCurrentLinePrefix + mIndent, mIndent);
    }


    @Override
    public Scribe append(CharSequence charSequence)
    {
        mStringBuilder.append(charSequence);
        return this;
    }


    @Override
    public Scribe newLine()
    {
        mStringBuilder.append(System.lineSeparator());
        mStringBuilder.append(mCurrentLinePrefix);
        return this;
    }


    @Override
    public String toString()
    {
        return mStringBuilder.toString();
    }
}
