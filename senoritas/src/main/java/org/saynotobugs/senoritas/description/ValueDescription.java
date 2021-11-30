package org.saynotobugs.senoritas.description;

import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Scribe;
import org.saynotobugs.senoritas.utils.ArrayIterable;

import java.util.Map;
import java.util.Optional;

import static org.saynotobugs.senoritas.description.LiteralDescription.NULL;


/**
 * A {@link Description} for all types of values. If possible, it delegates to a concrete {@link Description} for the type of the given value,
 * otherwise it delegates to {@link ToStringDescription}.
 */
public final class ValueDescription implements Description
{
    private final Object value;


    public ValueDescription(Object value)
    {
        this.value = value;
    }


    @Override
    public void describeTo(Scribe scribe)
    {
        Description description;
        if (value == null)
        {
            description = NULL;
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
            description = new IterableDescription(new ArrayIterable(value));
        }
        else
        {
            description = new ToStringDescription(value);
        }
        description.describeTo(scribe);
    }
}
