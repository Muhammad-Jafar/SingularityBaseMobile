Separated control is a principle where we must separate the UI state with the Composable Function. Ex:

❌ Wrong: This wrong because the state is within the component it self - make it impossible to interrupt the state change. 
```kotlin
fun ToggleButton(onStateChange: (selected: Boolean) -> Unit) {
	var toggleButtonState by remember { mutableStateOf(false) }
	LaunchEffect(toggleButtonState) {
		onStateChange(toggleButtonState)
	}

	Card(
		onClick = { toggleButtonState != toggleButtonState }
	) {
		... component
	}
}
```
Note: the pattern above also involve the [[lambda capture state antipattern]]

✅ Correct: The state is separated - make it easy to control the component state
```kotlin
fun ToggleButton(selected: Boolean, onClick: () -> Unit) {
	Card(
		onClick = onClick
	) {
		if(selected) {
			... draw selected state
		} else {
			... draw unselected state
		}
	}
}
```
Note: this version ensure that component does not do any logic other than rendering logic