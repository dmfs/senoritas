package org.saynotobugs.quality.quality.rxjava3;

import org.saynotobugs.quality.Quality;
import org.saynotobugs.quality.quality.rxjava3.adapters.RxTestAdapter;
import org.saynotobugs.quality.quality.rxjava3.adapters.RxSubjectAdapter;

import io.reactivex.rxjava3.schedulers.TestScheduler;


public interface TransformerTestStep<Up, Down>
{
    Iterable<Quality<RxTestAdapter<Down>>> qualities(TestScheduler scheduler, RxSubjectAdapter<Up> upstream);
}
