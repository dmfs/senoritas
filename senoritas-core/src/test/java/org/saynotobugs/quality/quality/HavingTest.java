package org.saynotobugs.quality.quality;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.quality.AllOf;
import org.saynotobugs.quality.quality.EqualTo;
import org.saynotobugs.quality.quality.Having;
import org.saynotobugs.quality.test.quality.Expects;
import org.saynotobugs.quality.test.quality.Fails;
import org.saynotobugs.quality.test.quality.Passes;

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