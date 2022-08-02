package org.saynotobugs.confidence.rxjava3;

import org.dmfs.jems2.Procedure;
import org.dmfs.jems2.Single;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;


@StaticFactories(value = "RxJava3", packageName = "org.saynotobugs.confidence.rxjava3")
public final class Error<Up> implements Procedure<RxSubjectAdapter<Up>>
{
    private final Single<Throwable> error;


    public Error(Throwable error)
    {
        this(() -> error);
    }


    public Error(Single<Throwable> error)
    {
        this.error = error;
    }


    @Override
    public void process(RxSubjectAdapter<Up> arg)
    {
        arg.onError(error.value());
    }
}
