package org.saynotobugs.senoritas.utils;

import org.dmfs.jems2.BiFunction;
import org.dmfs.jems2.Optional;
import org.dmfs.jems2.optional.Next;

import java.util.Iterator;


public final class OuterZipped<T, U, R> implements Iterable<R>
{
    private final Iterable<T> mLeft;
    private final Iterable<U> mRight;
    private final BiFunction<? super Optional<? extends T>, ? super Optional<? extends U>, ? extends R> mZipFunction;


    public OuterZipped(Iterable<T> left,
        Iterable<U> right,
        BiFunction<? super Optional<? extends T>, ? super Optional<? extends U>, ? extends R> zipFunction)
    {
        mLeft = left;
        mRight = right;
        mZipFunction = zipFunction;
    }


    @Override
    public Iterator<R> iterator()
    {
        Iterator<T> left = mLeft.iterator();
        Iterator<U> right = mRight.iterator();
        return new Iterator<R>()
        {
            @Override
            public boolean hasNext()
            {
                return left.hasNext() || right.hasNext();
            }


            @Override
            public R next()
            {
                return mZipFunction.value(new Next<>(left), new Next<>(right));
            }
        };
    }
}
