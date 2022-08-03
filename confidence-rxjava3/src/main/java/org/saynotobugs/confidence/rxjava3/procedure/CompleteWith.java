package org.saynotobugs.confidence.rxjava3.procedure;

import org.dmfs.jems2.iterable.Seq;
import org.dmfs.jems2.procedure.Composite;
import org.dmfs.jems2.procedure.DelegatingProcedure;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.rxjava3.RxSubjectAdapter;


@StaticFactories(value = "RxJava3", packageName = "org.saynotobugs.confidence.rxjava3")
public final class CompleteWith<Up> extends DelegatingProcedure<RxSubjectAdapter<Up>>
{

    @SafeVarargs
    public CompleteWith(Up... emissions)
    {
        this(new Seq<>(emissions));
    }


    public CompleteWith(Iterable<Up> emissions)
    {
        super(new Composite<>(
            new Emit<>(emissions),
            new Complete<>()
        ));
    }
}
