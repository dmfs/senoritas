package org.saynotobugs.quality.quality.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.quality.test.DescribesAs;
import org.saynotobugs.quality.quality.test.Expects;
import org.saynotobugs.quality.quality.test.Fails;
import org.saynotobugs.quality.quality.test.Passes;

import java.util.concurrent.atomic.AtomicInteger;

import static org.saynotobugs.quality.Assertion.assertThat;


class ParallelTest
{
    @Test
    void test()
    {
        AtomicInteger integer = new AtomicInteger(0);

        assertThat(new Parallel<>(new Supplies<>(new LessThan<>(999))),
            new AllOf<>(
                new Passes<>(() -> 99, () -> integer.incrementAndGet() % 999),
                new Fails<>(
                    () -> integer.incrementAndGet() % 1000,
                    new DescribesAs(new MatchesPattern("executions: ...\\R .+ supplied value <999>\\R  ..."))),
                new Fails<>(
                    () -> {
                        if (integer.incrementAndGet() % 999 == 0)
                        {
                            throw new RuntimeException("error");
                        }
                        else
                        {
                            return 0;
                        }
                    },
                    new DescribesAs(new MatchesPattern("executions: ...\\R .+ <java.lang.RuntimeException: error>\\R  ..."))),
                new Expects("running 1000 parallel execution, each supplies value less than <999>")
            ));
    }
}