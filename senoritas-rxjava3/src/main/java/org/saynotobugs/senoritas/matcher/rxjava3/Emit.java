package org.saynotobugs.senoritas.matcher.rxjava3;

import org.dmfs.jems2.Procedure;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.jems2.procedure.Batch;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.matcher.rxjava3.adapters.SubjectAdapter;


@StaticFactories("RxJava3")
public final class Emit<Up> implements Procedure<SubjectAdapter<Up>>
{
    private final Iterable<Up> mEmissions;


    @SafeVarargs
    public Emit(Up... emissions)
    {
        this(new Seq<>(emissions));
    }


    public Emit(Iterable<Up> emissions)
    {
        mEmissions = emissions;
    }


    @Override
    public void process(SubjectAdapter<Up> arg)
    {
        new Batch<>(arg::onNext).process(mEmissions);
    }
}
