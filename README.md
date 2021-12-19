[![Build Status](https://travis-ci.com/dmfs/senoritas.svg?branch=main)](https://app.travis-ci.com/github/dmfs/senoritas)
[![codecov](https://codecov.io/gh/dmfs/senoritas/branch/main/graph/badge.svg?token=3wGxOPmEEc)](https://codecov.io/gh/dmfs/senoritas)
[![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/dmfs/senoritas.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/dmfs/senoritas/context:java)

# Senoritas

Senoritas is a composable Assertion Framework. It's very much inspired by Hamcrest and based on the same idea. There are a couple of important differences though:

* In case of a mismatch, Hamcrest (for Java) needs to run the matcher again to get a mismatch description, Senoritas returns the result and (in case of a mismatch) the mismatch description in one go.
* Hamcrest Matchers (usually) stops at the first mismatch, Senoritas returns a complete picture.
* Senoritas makes it easier to produce comprehensible descriptions, closer to what Assertj or Google Truth produce
* In Senoritas the "Contains" Matcher has the same semantics as Java `Collection.contains(Object)`
* Senoritas has out ouf the box support for testing Matchers.
* By design, static matcher factory methods are generated, not manually coded.


Note, this library is still in its initial phase and things, including design and names, might change without notice.

## Things still to come

* feature parity with Hamcrest Core (a few matchers are still missing)


## Examples


### Simple mismatch description

```java
assertThat(List.of(1, 2, 10, 3, 4), new ContainsAllOf<>(1, 32, 11, 4));
```

```text
Expected:

contains <1> and
contains <32> and
contains <11> and
contains <4>

Actual:   

...
did not contain <32> and
did not contain <11>
...
```

### Structured (nested) mismatch description

```java
assertThat(new Integer[][] { new Integer[] { 1, 2, 3 }, new Integer[] { 4, 55, 6 }, new Integer[] { 6, 7, 88 } },
    new EqualTo<>(new Integer[][] { new Integer[] { 1, 2, 3 }, new Integer[] { 4, 5, 6 }, new Integer[] { 6, 7, 8 } }));
```

returns:

```text
Expected:

[
  [
    <1>,
    <2>,
    <3>
  ],
  [
    <4>,
    <5>,
    <6>
  ],
  [
    <6>,
    <7>,
    <8>
  ]
]

Actual:   

array that iterated [
  ...
  1: array that iterated [
    ...
    1: <55>
    ...
  ],
  2: array that iterated [
    ...
    2: <88>
  ]
]
```


### Hamcrest compatibility

```java
assertThat(new Seq<>(1, 2, 5, 10, 11), new Hamcrest<>(contains(1, 2, 3, 10, 12)));
```

```text
Expected:

iterable containing [<1>, <2>, <3>, <10>, <12>]

Actual:   

item 2: was <5>
```


### Another structured mismatch description

```java
assertThat(List.of(1, 2, 10, 11, 4),
    new Each<>(
        new NoneOf<>(
            new EqualTo<>(77),
            new EqualTo<>(10),
            new EqualTo<>(22),
            new GreaterThan<>(9))));
```

```text
Expected:

each value none of:
  <77>,
  <10>,
  <22>,
  greater than <9>

Actual:   

values [
  ...
  2:  was
    ...
    <10>
    ...
    greater than <9>,
  3:  was
    ...
    greater than <9>
  ...
]
```

## Senoritas vs Hamcest

This section gives an overview over some notable differences between Senoritas and Hamcrest.

General note on matching arrays: arrays (including ones of primitive types) can be matched with matchers to match `Iterable`s decorated with `arrayThat(…)`.

| Hamcrest | Senoritas |
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
