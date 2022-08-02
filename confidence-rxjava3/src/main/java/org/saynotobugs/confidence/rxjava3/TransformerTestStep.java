package org.saynotobugs.confidence.rxjava3;

import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.rxjava3.adapters.RxSubjectAdapter;
import org.saynotobugs.confidence.rxjava3.adapters.RxTestAdapter;

import io.reactivex.rxjava3.schedulers.TestScheduler;


public interface TransformerTestStep<Up, Down>
{
    Iterable<Quality<RxTestAdapter<Down>>> qualities(TestScheduler scheduler, RxSubjectAdapter<Up> upstream);
}
