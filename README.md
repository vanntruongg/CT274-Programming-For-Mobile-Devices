# CT274-Programming-For-Mobile-Devices

**1. Calculator App**

- This is a simple calculator app that allows you to perform calculations by entering 2 real numbers and an
  operator (+, -, *, /).

<table>
    <tr>
        <td><img src="https://github.com/vanntruongg/CT274-Programming-For-Mobile-Devices/assets/103330978/9c2247b7-7777-438c-bf3c-428ad3c3ecad" alt="Image 1" width="210" height="400"></td>
        <td><img src="https://github.com/vanntruongg/CT274-Programming-For-Mobile-Devices/assets/103330978/f146eda3-0575-4974-bbbd-b9c558de14e8" alt="Image 2" width="210" height="400"></td>
    </tr>
</table>

- Link: https://github.com/vanntruongg/CT274-Programming-For-Mobile-Devices/tree/master/Calculator

**2. Quiz App**
<table>
    <tr>
        <td><img src="https://github.com/vanntruongg/CT274-Programming-For-Mobile-Devices/assets/103330978/4dc3626d-02a8-4940-a34c-1e14993fbe0c" alt="Image 1" width="210" height="400"></td>
        <td><img src="https://github.com/vanntruongg/CT274-Programming-For-Mobile-Devices/assets/103330978/d00ea9c9-8c26-4701-bd34-7165ff306565" alt="Image 2" width="210" height="400"></td>
        <td><img src="https://github.com/vanntruongg/CT274-Programming-For-Mobile-Devices/assets/103330978/af3abac0-5363-4a6c-9b4c-4a6c0ad75688" alt="Image 3" width="210" height="400"></td>
    </tr>
    <tr>
        <td><img src="https://github.com/vanntruongg/CT274-Programming-For-Mobile-Devices/assets/103330978/ab7adb79-965a-464d-afa9-02b71abd1a10" alt="Image 4" width="210" height="400"></td>
    </tr>
</table>

Demo: https://drive.google.com/file/d/1ruokNfSpGAdV0fEV6PX5Sj4uPGYS5uS4/view

Link: https://github.com/vanntruongg/CT274-Programming-For-Mobile-Devices/tree/master/QuizApp

**3. Task Management**

- Practice:
    + using [RecyclerView](https://developer.android.com/develop/ui/views/layout/recyclerview).
    + using [Room](https://developer.android.com/training/data-storage/room) to interact with
      an [SQLite database](https://developer.android.com/reference/kotlin/android/database/sqlite/SQLiteDatabase).
    + interacting with the user interface (
      using [data binding](https://developer.android.com/topic/libraries/data-binding) and
      invoking [Intents](https://developer.android.com/reference/kotlin/android/content/Intent)).

<table>
    <tr>
        <td><img src="https://github.com/vanntruongg/CT274-Programming-For-Mobile-Devices/assets/103330978/a99c4200-902e-4da7-b0fe-fb5bfdfbb3ba" alt="Image 1" width="210" height="400"></td>
        <td><img src="https://github.com/vanntruongg/CT274-Programming-For-Mobile-Devices/assets/103330978/212b6687-f0b0-453e-bfd7-55b707ddeec4" alt="Image 2" width="210" height="400"></td>
        <td><img src="https://github.com/vanntruongg/CT274-Programming-For-Mobile-Devices/assets/103330978/20aa27fc-e3f6-4a60-969e-c2571e68673f" alt="Image 3" width="210" height="400"></td>
    </tr>
</table>

- Link: https://github.com/vanntruongg/CT274-Programming-For-Mobile-Devices/tree/master/News

**4. Wishes App**

- Objective:
    + Learn to use [Retrofit](https://square.github.io/retrofit/) to make API calls.
    + Understand how to use [Fragments](https://developer.android.com/guide/fragments).
    + Know how to use [SharedPreferences](https://developer.android.com/reference/android/content/SharedPreferences) for local data storage.
    + Familiarize yourself with supporting libraries (e.g., [Glide](https://www.geeksforgeeks.org/image-loading-caching-library-android-set-2/) for image loading, [CircleImageView](https://www.geeksforgeeks.org/how-to-create-a-circularimageview-in-android-using-hdodenhof-library/) for display
      circular image).

<table>
    <tr>
        <td><img src="https://github.com/vanntruongg/CT274-Programming-For-Mobile-Devices/assets/103330978/e551dae3-f1ec-4c4f-a665-7adc79766e60" alt="Image 1" width="210" height="400"></td>
        <td><img src="https://github.com/vanntruongg/CT274-Programming-For-Mobile-Devices/assets/103330978/e3a71866-6b90-48db-a0aa-b077bc8f40a9" alt="Image 2" width="210" height="400"></td>
        <td><img src="https://github.com/vanntruongg/CT274-Programming-For-Mobile-Devices/assets/103330978/9a733268-f5bf-4045-ab19-8f154138fb55" alt="Image 3" width="210" height="400"></td>
    </tr>
  <tr>
        <td><img src="https://github.com/vanntruongg/CT274-Programming-For-Mobile-Devices/assets/103330978/2c908163-2e7b-441c-90c3-25f3a6dd55a1" alt="Image 4" width="210" height="400"></td>
    </tr>
</table>

- Project Configuration:

    Add the following dependencies to the app module's gradle file:
```
  android {
    buildFeatures{
        viewBinding = true
    }
  }
  dependencies {
    // retrofit
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.google.code.gson:gson:2.10.1'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    
    // circle imageview
    implementation 'de.hdodenhof:circleimageview:3.1.0'
    
    // glide
    implementation 'com.github.bumptech.glide:glide:4.16.0'
}
 ```
- Link: https://github.com/vanntruongg/CT274-Programming-For-Mobile-Devices/tree/master/WishesApp

**5. MyMap**

- Objective:
  + Guide to programming Google Map on Android
  + Display the list of places using RecyclerView
  + Using Serializable Data Transmission via Intent
  + Use the AlertDialog

- Project Configuration:
  Add the plugin:
```
plugin {
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}
```

- Link: ...

