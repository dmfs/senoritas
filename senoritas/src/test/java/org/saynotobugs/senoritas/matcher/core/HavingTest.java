package org.saynotobugs.senoritas.matcher.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.matcher.test.Expects;
import org.saynotobugs.senoritas.matcher.test.Matches;
import org.saynotobugs.senoritas.matcher.test.Mismatches;

import static org.saynotobugs.senoritas.Assertion.assertThat;


class HavingTest
{
    @Test
    void test()
    {
        assertThat(new Having<>("toString", Object::toString, new EqualTo<>("123")),
            new AllOf<>(
                new Matches<>("123", 123),
                new Mismatches<>(124, "having toString \"124\""),
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
                new Matches<>("123", 123),
                new Mismatches<>(124, "mismatch \"124\""),
                new Expects("match \"123\"")));
    }
}