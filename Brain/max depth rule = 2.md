A composable function can only contains max 2 scope level.
scope 0:  is the root scope.
scope 1: is the collection-scope's content.

Good: Max Depth = 2
```kotlin
@Composable 
fun GoodCode() {
	// root: scope level 0
	val attr = SingularityTheme.attr
	// collumn as a collection of components
	Column(
		modifier = Modifier.verticalScroll(),
		verticalArrangement = Arrangement.spacedBy(attr.`medium-spacer`)
	) {
		// collumn: scope level 1
		HeaderContent()
		BodyContent(
			modifier = Modifier.weight(1f)
		)
		FooterContent()
	}
}

@Composable 
fun GoodCode() {
	// root: scope level 0, collection scope
	val attr = SingularityTheme.attr
	
	Icon1()
	Icon2()
	Icon3()
	...
}
```

Bad: Depth > 2
```kotlin
@Composable 
fun BadCode() {
	// root: scope level 0
	val attr = SingularityTheme.attr
	Column(
		modifier = Modifier.verticalScroll(),
		verticalArrangement = Arrangement.spacedBy(attr.`medium-spacer`)
	) {
		// collumn: scope level 1
		Row {
			// row: scope level 2
			// RULE VIOLATED : max depth rule = 3
			Icon(
				painter = painterResource(attr.ic_arrow_left),
				contentDescription = null
			)
			Text("Page Title)
		}
		BodyContent(
			modifier = Modifier.weight(1f)
		)
		FooterContent()
	}
}

// Should be extracted into:
@Composable
fun GoodCodeHeader() {
	val attr = SingularityTheme.attr
	Row {
		Icon(
			painter = painterResource(attr.ic_arrow_left),
			contentDescription = null
		)
		Text("Page Title)
	}
}
```