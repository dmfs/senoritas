package org.saynotobugs.quality.quality.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.quality.test.Expects;
import org.saynotobugs.quality.quality.test.Fails;
import org.saynotobugs.quality.quality.test.Passes;

import static org.saynotobugs.quality.Assertion.assertThat;


class HavingTest
{
    @Test
    void test()
    {
        assertThat(new Having<>("toString", Object::toString, new EqualTo<>("123")),
            new AllOf<>(
                new Passes<>("123", 123),
                new Fails<>(124, "having toString \"124\""),
                new Expects("having toString \"123\"")));
    }


    @Test
    void testDescriptions()
    {
        assertThat(new Having<>(
                new TextDescription("match"),
                new TextDescription("mismatch"),
                Object::toString,
                new EqualTo<>("123")),
            new AllOf<>(
                new Passes<>("123", 123),
                new Fails<>(124, "mismatch \"124\""),
                new Expects("match \"123\"")));
    }
}