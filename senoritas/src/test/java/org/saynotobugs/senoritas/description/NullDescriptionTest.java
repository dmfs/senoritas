package org.saynotobugs.senoritas.description;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.matcher.DescribesAs;

import static org.saynotobugs.senoritas.Assertion.assertThat;


class NullDescriptionTest
{

    @Test
    void test()
    {
        assertThat(new NullDescription(), new DescribesAs("<null>"));
    }

}