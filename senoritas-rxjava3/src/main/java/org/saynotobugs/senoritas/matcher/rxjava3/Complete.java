package org.saynotobugs.senoritas.matcher.rxjava3;

import org.dmfs.jems2.Procedure;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.matcher.rxjava3.adapters.SubjectAdapter;


@StaticFactories("RxJava3")
public final class Complete<Up> implements Procedure<SubjectAdapter<Up>>
{
    @Override
    public void process(SubjectAdapter<Up> arg)
    {
        arg.onComplete();
    }
}
