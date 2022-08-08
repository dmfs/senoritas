package org.saynotobugs.confidence.quality.composite;

import org.junit.jupiter.api.Test;
import org.saynotobugs.confidence.quality.charsequence.MatchesPattern;
import org.saynotobugs.confidence.quality.comparable.LessThan;
import org.saynotobugs.confidence.quality.supplier.Supplies;
import org.saynotobugs.confidence.test.quality.DescribesAs;
import org.saynotobugs.confidence.test.quality.Fails;
import org.saynotobugs.confidence.test.quality.HasDescription;
import org.saynotobugs.confidence.test.quality.Passes;

import java.util.concurrent.atomic.AtomicInteger;

import static org.saynotobugs.confidence.Assertion.assertThat;


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
                new HasDescription("running 1000 parallel execution, each supplies value less than <999>")
            ));
    }
}