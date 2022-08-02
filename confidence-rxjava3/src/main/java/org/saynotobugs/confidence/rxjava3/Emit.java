package org.saynotobugs.confidence.rxjava3;

import org.dmfs.jems2.Procedure;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.jems2.procedure.Batch;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.rxjava3.adapters.RxSubjectAdapter;

import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.core.SingleSource;


@StaticFactories("RxJava3")
public final class Emit<Up> implements Procedure<RxSubjectAdapter<Up>>
{
    private final Iterable<Up> mEmissions;


    /**
     * Creates a {@link Procedure} causes the upstream subject to emit the given value(s).
     * <p>
     * Note, this has no effect on {@link CompletableSource}s because they don't have any values.
     * <p>
     * {@link SingleSource}s and {@link MaybeSource}s can only emit a single value and trying to emit more than one value will
     * cause an Exception to be thrown. Also note that {@link SingleSource}s and {@link MaybeSource}s automatically complete when
     * a value is emitted.
     */
    @SafeVarargs
    public Emit(Up... emissions)
    {
        this(new Seq<>(emissions));
    }


    /**
     * Creates a {@link Procedure} causes the upstream subject to emit the given value(s).
     * <p>
     * Note, this has no effect on {@link CompletableSource}s because they don't have any values.
     * <p>
     * {@link SingleSource}s and {@link MaybeSource}s can only emit a single value and trying to emit more than one value will
     * cause an Exception to be thrown. Also note that {@link SingleSource}s and {@link MaybeSource}s automatically complete when
     * a value is emitted.
     */
    public Emit(Iterable<Up> emissions)
    {
        mEmissions = emissions;
    }


    @Override
    public void process(RxSubjectAdapter<Up> arg)
    {
        new Batch<>(arg::onNext).process(mEmissions);
    }
}
