[![Build Status](https://travis-ci.com/dmfs/senoritas.svg?branch=main)](https://app.travis-ci.com/github/dmfs/senoritas)
[![codecov](https://codecov.io/gh/dmfs/senoritas/branch/main/graph/badge.svg?token=3wGxOPmEEc)](https://codecov.io/gh/dmfs/senoritas)
[![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/dmfs/senoritas.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/dmfs/senoritas/context:java)

## **This project is now continued at https://github.com/saynotobugsorg/confidence and this repo is no longer maintained**

# Confidence

Confidence is a composable Assertion Framework. It's very much inspired by Hamcrest and based on the same idea. There are a couple of important differences
though:

* In case of a mismatch, Hamcrest (for Java) needs to run the matcher again to get a mismatch description, Confidence returns the result and (in case of a
  mismatch) the mismatch description in one go.
* Hamcrest Matchers (usually) stops at the first mismatch, Confidence returns a complete picture.
* Confidence makes it easier to produce comprehensible descriptions, closer to what Assertj or Google Truth produce
* In Confidence the "Contains" Matcher has the same semantics as Java `Collection.contains(Object)`
* Confidence has out ouf the box support for testing Matchers.
* By design, static matcher factory methods are generated, not manually coded.

Note, this library is still in its initial phase and things, including design and names, might change without notice.

## Things still to come

* feature parity with Hamcrest Core (a few matchers are still missing)

## Examples

### Simple mismatch description

```java
assertThat(asList(1,2,10,3,4),contains(1,32,11,4));
```

```text
Expected:

contains <1>
  and
  contains <32>
  and
  contains <11>
  and
  contains <4>

Actual:   

{ ...
  did not contain <32>
  and
  did not contain <11>
  ... }
```

### Structured (nested) mismatch description

```java
assertThat(
    new int[][]{new int[]{1,2,3},new int[]{4,7,8},new int[]{6,7,8,9}},
    equalTo(new int[][]{new int[]{1,2,3},new int[]{4,5,6},new int[]{6,7,8}}));
```

returns:

```text
Expected:

[ [ <1>,
    <2>,
    <3> ],
  [ <4>,
    <5>,
    <6> ],
  [ <6>,
    <7>,
    <8> ] ]

Actual:   

array that iterated [ ...
  1: array that iterated [ ...
    1: <7>,
    2: <8> ],
  2: array that iterated [ ...
    3: unexpected <9> ] ]
```

### Hamcrest compatibility

```java
assertThat(new Seq<>(1,2,5,10,11),hamcrest(contains(1,2,3,10,12)));
```

```text
Expected:

iterable containing [<1>, <2>, <3>, <10>, <12>]

Actual:   

item 2: was <5>
```

## Simple implementation of a Matcher

Writing new Matchers can be as simple as

```java

@StaticFactories("Core")
public final class EmptyString extends MatcherComposition<String>
{

    public EmptyString()
    {
        super(new Satisfies<>(
            String::isEmpty,
            new TextDescription("an empty String")
        ));
    }
}
```

This creates a new Matcher composition based on an existing Matcher `Satisfies`.
`Satisfies` takes a ` Predicate` that must be satisfied for the Matcher to match and a `Description` of the expectation. By default, the mismatch `Description`
is the actual value, but `Satisfies` takes an optional argument to create a more adequate mismatch `Description` for a given actual value.

The annotation `@StaticFactories("Core")` ensures a static factory methods like the following is automatically created in a class called `Core`:

```java
  public static EmptyString emptyString(){
    return new EmptyString();
    }
```

## Confidence vs Hamcrest

This section gives an overview over some notable differences between Confidence and Hamcrest.

General note on matching arrays: arrays (including ones of primitive types) can be matched with matchers to match `Iterable`s decorated with `arrayThat(…)`.

| Hamcrest | Confidence |
|---|---|
| `contains(...)` | `iterates(...)` |
| `containsInAnyOrder(...)` | `iteratesInAnyOrder(...)` |
| `iterableWithSize(...)` | `hasNumberOfElements(...)` |
| `hasItem(...)` | `contains(...)` |
| `hasItems(...)` | `contains(...)` |
| `everyItem(...)` | `each(...)` |
| `sameInstance(...)`, `theInstance(...)` | `sameAs(...)` |
| `matchesRegex(...)`, `matchesPattern(...)` | `matchesPattern(...)` |
| `array(...)` | `arrayThat(iterates(...))`* |
| `hasItemInArray(...)` | `arrayThat(contains(...))`* | 
| `arrayWithSize(...)` | `arrayThat(hasNumberOfElements(...))`* |

*works with arrays of primitive types

## Experimental RXJava3 support

Although RXJava comes with built-in test support it can be tedious to test. Confidence gives you the means to focus on the "what" so you don't have to care
about the "how".

### Example

This is how a test of an RXJava Flowable would look like.

```java
assertThat((TestScheduler scheduler)->Flowable.interval(10,TimeUnit.SECONDS,scheduler).take(10),
    is(publisherThat(
    immediately(emitsNothing()),
    within(ofSeconds(10),emits(0L)),
    within(ofSeconds(10),emits(1L)),
    within(ofSeconds(10),emits(2L)),
    within(ofSeconds(10),emits(3L)),
    within(ofSeconds(10),emits(4L)),
    within(ofSeconds(10),emits(5L)),
    within(ofSeconds(10),emits(6L)),
    within(ofSeconds(10),emits(7L)),
    within(ofSeconds(10),emits(8L)),
    within(ofSeconds(10),emits(9L)),
    immediately(completes())
    )));
```

RXJava 3 support is still in an early, experimental state and may be subject to change.
