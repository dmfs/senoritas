package org.saynotobugs.confidence.utils;

import org.dmfs.jems2.Procedure;


public final class Delimited<T> implements Procedure<Iterable<? extends T>>
{
    private final Runnable mBefore;
    private final Runnable mBetween;
    private final Runnable mAfter;
    private final Procedure<? super T> mEach;


    public Delimited(Runnable before, Runnable between, Runnable after, Procedure<? super T> each)
    {
        mBefore = before;
        mBetween = between;
        mAfter = after;
        mEach = each;
    }


    @Override
    public void process(Iterable<? extends T> arg)
    {
        mBefore.run();
        boolean first = true;
        for (T a : arg)
        {
            if (first)
            {
                first = false;
            }
            else
            {
                mBetween.run();
            }
            mEach.process(a);
        }
        mAfter.run();
    }
}
