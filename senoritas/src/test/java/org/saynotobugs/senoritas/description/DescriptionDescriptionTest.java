package org.saynotobugs.senoritas.description;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.matcher.DescribesAs;

import static org.saynotobugs.senoritas.Assertion.assertThat;


class DescriptionDescriptionTest
{

    @Test
    void test()
    {
        assertThat(
            new DescriptionDescription(new TextDescription("abc")),
            new DescribesAs("\n  ----\n  abc\n  ----"));
    }

}