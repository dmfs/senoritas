package org.saynotobugs.quality.description;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.quality.test.DescribesAs;

import static org.saynotobugs.quality.Assertion.assertThat;


class ToStringDescriptionTest
{

    @Test
    void testNonEmpty()
    {
        assertThat(new ToStringDescription(new Object()
            {
                @Override
                public String toString()
                {
                    return "test to String";
                }
            }),
            new DescribesAs("<test to String>"));
    }

}