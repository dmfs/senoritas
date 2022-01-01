package org.saynotobugs.senoritas.matcher.rxjava3;

import org.dmfs.jems2.Procedure;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;

import io.reactivex.rxjava3.processors.PublishProcessor;


@StaticFactories("RxJava3")
public final class Complete<Up> implements Procedure<PublishProcessor<Up>>
{
    @Override
    public void process(PublishProcessor<Up> arg)
    {
        arg.onComplete();
    }
}
