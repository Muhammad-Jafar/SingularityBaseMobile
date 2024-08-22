When user click a button, ui will receive a **click event**, but what does that means?

"Events" by them self are meaning less. Therefore, events need to be translated into an **Intention**. For example: "What do you want when you press the save button?" - "I want to save the document". The event of "Press Save Button" is translated into "SaveDocument" intention.

**UI is responsible to translate events into intention**. Into something **meaningful**. The responsibility of translating "Meaningless events" into "Meaningful Intentions" is called the "**Event to Intent Translation**" and the process happens in the **Event to Intent Translation Layer**.

**The case with MVVM architecture:**
ViewModel cannot depends to the Screen. They don't know what happen in the UI and  they don't need to know. - Therefore; **UI Must not send event to the viewmodel**. UI Must send "**Intention**" of what process to execute.

see: [[ui flow dogma]]
