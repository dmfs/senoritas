package org.saynotobugs.confidence.quality;

import org.junit.jupiter.api.Test;
import org.saynotobugs.confidence.test.quality.Expects;
import org.saynotobugs.confidence.test.quality.Fails;
import org.saynotobugs.confidence.test.quality.Passes;

import java.util.Map;

import static org.saynotobugs.confidence.Assertion.assertThat;


class EntryOfTest
{

    @Test
    void test()
    {
        assertThat(new EntryOf<>(new EqualTo<>(12), new EqualTo<>("abc")),
            new AllOf<>(
                new Passes<>(entry(12, "abc")),
                new Fails<>(entry(13, "ab"), "Entry { <12>: \"abc\" }"),
                new Expects("Entry { <12>: \"abc\" }")
            ));
    }


    private static <K, V> Map.Entry<K, V> entry(K key, V value)
    {
        return new Map.Entry<K, V>()
        {
            @Override
            public K getKey()
            {
                return key;
            }


            @Override
            public V getValue()
            {
                return value;
            }


            @Override
            public V setValue(V v)
            {
                throw new UnsupportedOperationException("not implemented");
            }
        };
    }
}