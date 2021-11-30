package org.saynotobugs.senoritas.utils;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.core.Iterates;

import static org.saynotobugs.senoritas.Assertion.assertThat;


class ArrayIterableTest
{

    @Test
    void testEmpty()
    {
        assertThat(
            new ArrayIterable(new int[] {}),
            new Iterates<>());
    }


    @Test
    void testInt()
    {
        assertThat(
            new ArrayIterable(new int[] { 1, 2, 3 }),
            new Iterates<>(1, 2, 3));
    }


    @Test
    void testBoolean()
    {
        assertThat(
            new ArrayIterable(new boolean[] { true, false, false, true }),
            new Iterates<>(true, false, false, true));
    }

}