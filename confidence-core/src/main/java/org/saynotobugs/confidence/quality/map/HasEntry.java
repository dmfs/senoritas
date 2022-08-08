package org.saynotobugs.confidence.quality.map;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.quality.composite.Has;
import org.saynotobugs.confidence.quality.composite.QualityComposition;
import org.saynotobugs.confidence.quality.iterable.Contains;
import org.saynotobugs.confidence.quality.object.EqualTo;
import org.saynotobugs.confidence.quality.trivial.Anything;

import java.util.Map;


@StaticFactories(value = "Core", packageName = "org.saynotobugs.confidence.quality")
public final class HasEntry<K, V> extends QualityComposition<Map<K, V>>
{
    public HasEntry(K key)
    {
        this(new EqualTo<>(key));
    }


    public HasEntry(Quality<? super K> key)
    {
        this(new EqualTo<>(key), new Anything());
    }


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
        super(new Has<>(Map::entrySet, new Contains<>(new EntryOf<>(keyQuality, valueQuality))));
    }
}
