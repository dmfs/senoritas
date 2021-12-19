package org.saynotobugs.senoritas.matcher.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.test.Expects;
import org.saynotobugs.senoritas.matcher.test.Matches;

import java.util.concurrent.atomic.AtomicInteger;

import static org.saynotobugs.senoritas.Assertion.assertThat;


class ParallelTest
{
    @Test
    void test()
    {
        AtomicInteger integer = new AtomicInteger(0);

        assertThat(new Parallel<>(new Supplies<>(new LessThan<>(999))),
            new AllOf<>(
                new Matches<>(() -> 99, () -> integer.incrementAndGet() % 999),
                // mismatches don't work properly yet here need to dig into that by adding test cases for the involved matchers
       /*         new Mismatches<>(
                    () -> integer.incrementAndGet() % 1000,
                    new DescribesAs(new MatchesPattern("executions: ...\\R .+ supplied value <999>\\R  ..."))),*/
                new Expects("running 1000 parallel execution, each supplies value less than <999>")
            ));
    }
}