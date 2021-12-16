package org.saynotobugs.senoritas;

import org.dmfs.srcless.annotations.composable.Composable;


@Composable(packageName = "org.saynotobugs.senoritas.matcher")
public interface Matcher<T>
{
    Verdict match(T actual);

    Description expectation();
}
