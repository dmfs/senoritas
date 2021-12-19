package org.saynotobugs.senoritas.utils;

import org.dmfs.jems2.BiFunction;
import org.dmfs.jems2.Function;
import org.dmfs.jems2.Pair;


/**
 * Un-pairs the {@link Pair} argument of a function.
 * <p>
 * TODO: move to jems.
 */
public final class UnPaired<Left, Right, Result> implements Function<Pair<Left, Right>, Result>
{

    private final BiFunction<? super Left, ? super Right, ? extends Result> mDelegate;


    public UnPaired(BiFunction<? super Left, ? super Right, ? extends Result> delegate)
    {
        mDelegate = delegate;
    }


    @Override
    public Result value(Pair<Left, Right> pair)
    {
        return mDelegate.value(pair.left(), pair.right());
    }
}
