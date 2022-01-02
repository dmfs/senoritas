package org.saynotobugs.senoritas.matcher.rxjava3;

import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.matcher.rxjava3.adapters.RxTestAdapter;
import org.saynotobugs.senoritas.matcher.rxjava3.adapters.SubjectAdapter;

import io.reactivex.rxjava3.schedulers.TestScheduler;


public interface TransformerEvent<Up, Down>
{

    Matcher<RxTestAdapter<Down>> matcher(TestScheduler scheduler, SubjectAdapter<Up> upstream);
}
