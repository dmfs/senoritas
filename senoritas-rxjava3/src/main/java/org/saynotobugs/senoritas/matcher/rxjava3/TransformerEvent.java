package org.saynotobugs.senoritas.matcher.rxjava3;

import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.matcher.rxjava3.utils.RxTestAdapter;

import io.reactivex.rxjava3.processors.PublishProcessor;
import io.reactivex.rxjava3.schedulers.TestScheduler;


public interface TransformerEvent<Up, Down>
{
    Matcher<RxTestAdapter<Down>> matcher(TestScheduler scheduler, PublishProcessor<Up> upstream);
}
