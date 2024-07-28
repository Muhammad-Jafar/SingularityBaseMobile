Remember that the purpose of system token is to create **Thinking Framework**, for the Users, Developers, Designer, Content Creators, everybody. So everybody have same thinking framework. The rule is as follows:

- **naming by role**.
  An Attribute's name must convey its role. ex: `color-on-primary`.
  Conveying that the token Role is to modify a color of elements on a `color-primary` base.
- **refer to static reference**.
  Token attribute must refer to static value.
  ex: 
  ```kotlin
	  val color-on-primary get() = ColorRed30
	  
	  // reference must be static
	  internal val ColorRed30 = Color(0xf3...)
	```

- **no context**
  Token must not submit to a context. Token is like unix id one for anybody. No Context, No Body can have same token attribute.
  Wrong: `Color.color-primary`
  Good: `color-primary`