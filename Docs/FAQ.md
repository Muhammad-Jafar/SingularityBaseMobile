## FAQ

- `#MissingSdk`, `#MissingLocalProperties`, `#LibraryIsNotAnAndroidModule`.
  These issues are known to occur on Windows machines. You need to create a symbolic link from
  your `local.properties` file to the `Library/` directory.
  **Warning**: Copying the `local.properties` file to the Library project is not recommended.

- `#LaunchError`, `#NoDefaultActivity` `#LaunchingExampleActivity`. To launch the example activity
  you need to edit launch configuration to launch to a Specific Activity, and set the target to
  ExampleActivity.
  see: [Launching Specific Activity](https://developer.android.com/studio/run/rundebugconfig?hl=id#:~:text=%3C/intent%2Dfilter%3E-,Specified%20Activity,-%2D%20Meluncurkan%20aktivitas%20aplikasi).
