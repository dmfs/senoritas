package org.saynotobugs.senoritas;

import org.dmfs.srcless.annotations.composable.Composable;


@Composable(packageName = "org.saynotobugs.senoritas.verdict")
public interface Verdict
{
    boolean isSuccess();

    Description description();
}
