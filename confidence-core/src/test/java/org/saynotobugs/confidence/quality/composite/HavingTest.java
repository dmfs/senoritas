package org.saynotobugs.confidence.quality.composite;

import org.junit.jupiter.api.Test;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.quality.grammar.Has;
import org.saynotobugs.confidence.quality.object.EqualTo;
import org.saynotobugs.confidence.test.quality.Description;
import org.saynotobugs.confidence.test.quality.Fails;
import org.saynotobugs.confidence.test.quality.Passes;

import static org.saynotobugs.confidence.Assertion.assertThat;


class HavingTest
{
    @Test
    void test()
    {
        assertThat(new Having<>("toString", Object::toString, new EqualTo<>("123")),
            new AllOf<>(
                new Passes<>("123", 123),
                new Fails<>(124, "having toString \"124\""),
                new Has<>(new Description("having toString \"123\""))));
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
                new Has<>(new Description("match \"123\""))));
    }
}