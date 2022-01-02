package org.saynotobugs.senoritas.matcher.rxjava3;

import org.dmfs.jems2.Procedure;
import org.dmfs.jems2.Single;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.matcher.rxjava3.adapters.SubjectAdapter;


@StaticFactories("RxJava3")
public final class Error<Up> implements Procedure<SubjectAdapter<Up>>
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
    public void process(SubjectAdapter<Up> arg)
    {
        arg.onError(error.value());
    }
}
