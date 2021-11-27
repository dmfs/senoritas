package org.saynotobugs.senoritas.description;

import java.util.Optional;


@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public final class OptionalDescription extends DelegatingDescription
{
    public OptionalDescription(Optional<?> value)
    {
        super(
            value.isPresent()
                ? new Composite("", new TextDescription("<present "), new ValueDescription<>(value.get()), new TextDescription(">"))
                : new TextDescription("<empty>")
        );
    }
}
