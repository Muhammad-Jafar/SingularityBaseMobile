Token reference is what a token referring to.
Let say we have `color-on-primary` that referring to static color of `Colors.whiteButNotSoMuchWhite`. The token reference must obey the following rules:

- **encapsulated**:
  Token Reference must be internal (non-transitive / encapsulated) and static.
  ex: `internal val SomeReference get() = Color.White`.
  Notice that the return of the reference is **Static** (Color.White).
- **descriptive naming**:
  Reference must be descriptive and expose its full identity within its name.
  `internal val TextSansSerif32NormalLS0LH40 get() = Color.White`.
  The idea is to make it not duplicated so multiple token can refer single clear identity reference.
