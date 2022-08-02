package org.saynotobugs.confidence.quality;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Quality;

import java.util.Map;


@StaticFactories("Core")
public final class HasEntry<K, V> extends QualityComposition<Map<K, V>>
{
    public HasEntry(K key, V value)
    {
        this(new EqualTo<>(key), new EqualTo<>(value));
    }


    public HasEntry(K key, Quality<? super V> valueQuality)
    {
        this(new EqualTo<>(key), valueQuality);
    }


    public HasEntry(Quality<? super K> keyQuality, Quality<? super V> valueQuality)
    {
        super(new Having<>(Map::entrySet, new Contains<>(new EntryOf<>(keyQuality, valueQuality))));
    }
}
