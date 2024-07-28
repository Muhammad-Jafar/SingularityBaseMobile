Token reference is what a token referring to.
Let say we have `color-on-primary` that referring to static color of `Colors.whiteButNotSoMuchWhite`. The token reference must obey the following rules:

- **encapsulated**:
  Token Reference must be internal (non-transitive / encapsulated) and static.
  ex: `internal val SomeReference = Color.White`.
- **descriptive naming**:
  Reference must be descriptive and expose its full identity within its name.
  ```kotlin
	  internal val TextSansSerif32NormalLS0LH40 = TextStyle(  
		    fontFamily = FontFamily.SansSerif,  
		    fontSize = 32.sp,  
		    fontWeight = FontWeight.Normal,  
		    letterSpacing = 0.sp,  
		    lineHeight = 40.sp,  
		)
	```
  The idea is to make it not duplicated so multiple token can refer to single clear identity reference.
