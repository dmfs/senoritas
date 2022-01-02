package org.saynotobugs.senoritas.description;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.test.DescribesAs;

import static org.saynotobugs.senoritas.Assertion.assertThat;


class NumberDescriptionTest
{

    @Test
    void testInt()
    {
        assertThat(new NumberDescription(123), new DescribesAs("<123>"));
    }


    @Test
    void testFloat()
    {
        assertThat(new NumberDescription(123.23f), new DescribesAs("<123.23>"));
    }


    @Test
    void testDouble()
    {
        assertThat(new NumberDescription(123.23d), new DescribesAs("<123.23>"));
    }
}