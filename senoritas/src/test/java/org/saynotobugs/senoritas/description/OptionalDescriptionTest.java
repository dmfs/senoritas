package org.saynotobugs.senoritas.description;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.matcher.DescribesAs;

import java.util.Optional;

import static org.saynotobugs.senoritas.Assertion.assertThat;


class OptionalDescriptionTest
{

    @Test
    void testPresent()
    {
        assertThat(new OptionalDescription(Optional.of(123)),
            new DescribesAs("<present <123> >"));

        assertThat(new OptionalDescription(Optional.of("abc")),
            new DescribesAs("<present \"abc\" >"));
    }


    @Test
    void testEmpty()
    {
        assertThat(new OptionalDescription(Optional.empty()),
            new DescribesAs("<empty>"));
    }

}