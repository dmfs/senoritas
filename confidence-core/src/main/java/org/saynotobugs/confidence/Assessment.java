package org.saynotobugs.confidence;

import org.dmfs.srcless.annotations.composable.Composable;


@Composable(packageName = "org.saynotobugs.confidence.assessment")
public interface Assessment
{
    boolean isSuccess();

    Description description();
}
