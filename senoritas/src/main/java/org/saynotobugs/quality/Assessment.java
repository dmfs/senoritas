package org.saynotobugs.quality;

import org.dmfs.srcless.annotations.composable.Composable;


@Composable(packageName = "org.saynotobugs.quality.assessment")
public interface Assessment
{
    boolean isSuccess();

    Description description();
}
