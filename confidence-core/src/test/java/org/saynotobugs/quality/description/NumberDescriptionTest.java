package org.saynotobugs.quality.description;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.test.quality.DescribesAs;

import static org.saynotobugs.quality.Assertion.assertThat;


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