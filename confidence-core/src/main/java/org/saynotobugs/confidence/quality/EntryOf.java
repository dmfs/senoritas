package org.saynotobugs.confidence.quality;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.description.Composite;
import org.saynotobugs.confidence.description.TextDescription;

import java.util.Map;


@StaticFactories("Core")
public final class EntryOf<K, V> extends QualityComposition<Map.Entry<K, V>>
{
    public EntryOf(K key, V value)
    {
        this(new EqualTo<>(key), new EqualTo<>(value));
    }


    public EntryOf(K key, Quality<? super V> valueQuality)
    {
        this(new EqualTo<>(key), valueQuality);
    }


    public EntryOf(Quality<? super K> key, Quality<? super V> value)
    {
        super(new DescriptionUpdated<>(description -> new Composite(
            new TextDescription("Entry { "),
            key.description(),
            new TextDescription(": "),
            value.description(),
            new TextDescription(" }")),
            new AllOf<>(
                new Having<>(Map.Entry::getKey, key),
                new Having<>(Map.Entry::getValue, value)
            )));
    }
}
