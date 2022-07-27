package org.saynotobugs.quality.quality.rxjava3;

import org.dmfs.jems2.Procedure;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.quality.quality.rxjava3.adapters.RxSubjectAdapter;

import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.core.SingleTransformer;


@StaticFactories("RxJava3")
public final class Complete<Up> implements Procedure<RxSubjectAdapter<Up>>
{
    /**
     * Creates a {@link Procedure} that completes the upstream subject without any additional values.
     * <p>
     * Note, this has no effect when testing a {@link SingleTransformer} because {@link SingleSource} can not complete without a value.
     */
    @Override
    public void process(RxSubjectAdapter<Up> arg)
    {
        arg.onComplete();
    }
}
