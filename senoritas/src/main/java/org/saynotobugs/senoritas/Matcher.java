package org.saynotobugs.senoritas;

public interface Matcher<T>
{
    Verdict match(T actual);

    Description expectation();
}
