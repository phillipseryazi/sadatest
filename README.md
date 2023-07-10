### SADA test App
This is an app that shows a list of git repositories.

### How To Set Up From Zip
- Download zip file provided via email
- Unzip using zip software of choice
- Open Android Studio, on the initial menu select the `Open` option.
- Look for the project folder you unzipped in your file system, it should be named `sadatest`, open it.
- Wait for Android Studio to finish building the project.
- Make sure you have a device connected or emulator running.
- Click on the `Run` dropdown and select the `Run 'app'` option. Optionally you can click on the green run button.
- This should run the app on your emulator or device.

### How To Run Tests
- In your Android Studio terminal run `./gradlew connectedAndroidTest` for instrumentation tests
- In your Android Studio terminal run `./gradlew test` for unit tests
- Alternatively, you can right click on the source set `com.example.sadatest(androidTest)` and select `Run 'Tests' in`, similarly
  you can do the same with the source set `com.example.sadatest(test)`

### Assumptions Made
- I implemented a list with expandable items as according to the design it looks this is the expectation.
- I initially thought this would be an offline first app but after considering the lack of pagination and the 
existence of a busy screen with a retry button I figured, normally offline first apps show data that has been previously downloaded
and not a retry screen.
