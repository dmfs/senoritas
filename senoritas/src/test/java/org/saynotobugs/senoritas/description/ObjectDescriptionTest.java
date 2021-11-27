package org.saynotobugs.senoritas.description;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.matcher.DescribesAs;

import static org.saynotobugs.senoritas.Assertion.assertThat;


class ObjectDescriptionTest
{

    @Test
    void test()
    {
        assertThat(new ObjectDescription(new Object()
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