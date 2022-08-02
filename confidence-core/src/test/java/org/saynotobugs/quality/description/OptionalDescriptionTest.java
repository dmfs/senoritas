package org.saynotobugs.quality.description;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.test.quality.DescribesAs;

import java.util.Optional;

import static org.saynotobugs.quality.Assertion.assertThat;


class OptionalDescriptionTest
{

    @Test
    void testPresent()
    {
        assertThat(new OptionalDescription(Optional.of(123)),
            new DescribesAs("<present <123>>"));

        assertThat(new OptionalDescription(Optional.of("abc")),
            new DescribesAs("<present \"abc\">"));
    }


    @Test
    void testEmpty()
    {
        assertThat(new OptionalDescription(Optional.empty()),
            new DescribesAs("<empty>"));
    }

}