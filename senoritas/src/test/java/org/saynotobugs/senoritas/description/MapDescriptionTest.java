package org.saynotobugs.senoritas.description;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.test.DescribesAs;

import java.util.HashMap;
import java.util.Map;

import static org.saynotobugs.senoritas.Assertion.assertThat;


class MapDescriptionTest
{

    @Test
    void testEmptyMap()
    {
        assertThat(
            new MapDescription(new HashMap<>()),
            new DescribesAs("{  }"));
    }


    @Test
    void testNonEmptyMap()
    {
        Map<String, String> map = new HashMap<>();
        map.put("a", "x");
        assertThat(
            new MapDescription(map),
            new DescribesAs("{ \"a\": \"x\" }"));
    }


    @Test
    void testMapWithManyEntries()
    {
        Map<String, String> map = new HashMap<>();
        map.put("a", "x");
        map.put("b", "y");
        map.put("c", "z");
        assertThat(
            new MapDescription(map),
            new DescribesAs("{ \"a\": \"x\",\n  \"b\": \"y\",\n  \"c\": \"z\" }"));
    }

}