When a lambda is capturing a state the component will be recomposed when ever the state is changed.
It might sounds obvious but it will be a problem when the event is propagated.
When the event is propagated to the parent, the parent will also be recomposed because the lamda is recomposed.

❌ Wrong:
```kotlin
var isActive = remember{ mutableBooleanStateOf(false) }
var userName = remember{ mutableStateOf("")}

...
Card(
	onClick = {
		if(isActive) { // <- This lambda capture `isActive` state

			val data = userName // <- this lambda capture `userName` state
			...
		}
	}
) {

}
...
```
