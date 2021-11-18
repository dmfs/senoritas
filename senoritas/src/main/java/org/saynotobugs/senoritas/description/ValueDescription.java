package org.saynotobugs.senoritas.description;

import org.dmfs.jems2.iterable.Seq;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Scribe;

import java.util.Map;
import java.util.Optional;

import static org.saynotobugs.senoritas.description.NullDescription.nullDescription;


/**
 * A {@link Description} for all types of values. If possible, it delegates to a concrete {@link Description} for the type of the given value,
 * otherwise it delegates to {@link ObjectDescription}.
 */
public final class ValueDescription<T> implements Description
{
    private final T value;


    public ValueDescription(T value)
    {
        this.value = value;
    }


    @Override
    public void describeTo(Scribe sink)
    {
        Description description;
        if (value == null)
        {
            description = nullDescription;
        }
        else if (value instanceof Number)
        {
            description = new NumberDescription((Number) value);
        }
        else if (value instanceof CharSequence)
        {
            description = new CharSequenceDescription((CharSequence) value);
        }
        else if (value instanceof Map.Entry)
        {
            description = new MapEntryDescription((Map.Entry<?, ?>) value);
        }
        else if (value instanceof Iterable)
        {
            description = new IterableDescription((Iterable<?>) value);
        }
        else if (value instanceof Map)
        {
            description = new MapDescription((Map<?, ?>) value);
        }
        else if (value instanceof Optional)
        {
            description = new OptionalDescription((Optional<?>) value);
        }
        else if (value instanceof Description)
        {
            description = new DescriptionDescription((Description) value);
        }
        else if (value.getClass().isArray())
        {
            description = new IterableDescription(new Seq<>((Object[]) value));
        }
        else
        {
            description = new ObjectDescription(value);
        }
        description.describeTo(sink);
    }
}
