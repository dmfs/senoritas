package org.saynotobugs.quality.quality.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.quality.test.Expects;
import org.saynotobugs.quality.quality.test.Fails;
import org.saynotobugs.quality.quality.test.Passes;

import java.util.HashMap;
import java.util.Map;

import static org.saynotobugs.quality.Assertion.assertThat;


class HasEntryTest
{

    @Test
    void test()
    {
        Map<String, String> map1 = new HashMap<>();
        map1.put("k1", "v1");
        Map<String, String> map2 = new HashMap<>();
        map2.put("k2", "v2");

        assertThat(new HasEntry<>("k1", "v1"),
            new AllOf<>(
                new Passes<>(map1),
                new Fails<>(new HashMap<String, String>(), "{  } did not contain Entry { \"k1\": \"v1\" }"),
                new Fails<>(map2, "{ \"k2\": \"v2\" } did not contain Entry { \"k1\": \"v1\" }"),
                //  new Fails<>(Map.of("k2", "v2"), new DescribesAs("")),
                new Expects("contains Entry { \"k1\": \"v1\" }")
            ));

    }

}