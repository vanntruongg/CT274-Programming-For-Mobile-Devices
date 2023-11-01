# CT274-Programming-For-Mobile-Devices

**1. Calculator App**

- This is a simple calculator app that allows you to perform calculations by entering 2 real numbers and an
  operator (+, -, *, /).

<table>
    <tr>
        <td><img src="https://github.com/vanntruongg/CT274-Programming-For-Mobile-Devices/assets/103330978/3d3ffc4d-eea6-4353-b2e2-8f5924d42f63" alt="Image 1" width="210" height="400"></td>
        <td><img src="https://github.com/vanntruongg/CT274-Programming-For-Mobile-Devices/assets/103330978/36850d7a-3785-46bd-9263-67ee0ce35e59" alt="Image 2" width="210" height="400"></td>
    </tr>
</table>

- Link: https://github.com/vanntruongg/CT274-Programming-For-Mobile-Devices/tree/master/Calculator

**2. Quiz App**
<table>
    <tr>
        <td><img src="https://github.com/vanntruongg/CT274-Programming-For-Mobile-Devices/assets/103330978/0d3f19a3-227b-4269-9987-8988b18a4034" alt="Image 1" width="210" height="400"></td>
        <td><img src="https://github.com/vanntruongg/CT274-Programming-For-Mobile-Devices/assets/103330978/5399222a-f63b-4c3f-99ac-1d464b0497bc" alt="Image 2" width="210" height="400"></td>
        <td><img src="https://github.com/vanntruongg/CT274-Programming-For-Mobile-Devices/assets/103330978/559c4812-a5a1-434d-a892-5c85c47d8de3" alt="Image 3" width="210" height="400"></td>
    </tr>
    <tr>
        <td><img src="https://github.com/vanntruongg/CT274-Programming-For-Mobile-Devices/assets/103330978/5e5180ee-282c-4b1c-ab94-e9e2ea0da754" alt="Image 4" width="210" height="400"></td>
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
        <td><img src="https://github.com/vanntruongg/CT274-Programming-For-Mobile-Devices/assets/103330978/fb8c6270-5ea6-408a-b92f-2d1455e1b55c" alt="Image 1" width="210" height="400"></td>
        <td><img src="https://github.com/vanntruongg/CT274-Programming-For-Mobile-Devices/assets/103330978/90aa6a2c-4415-49a1-bcf4-dd0ae230f950" alt="Image 2" width="210" height="400"></td>
        <td><img src="https://github.com/vanntruongg/CT274-Programming-For-Mobile-Devices/assets/103330978/36e77db4-e36e-4c5b-bac3-9c1a125cc974" alt="Image 3" width="210" height="400"></td>
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
        <td><img src="https://github.com/vanntruongg/CT274-Programming-For-Mobile-Devices/assets/103330978/9ef60c2c-62ee-4e7e-a625-3f7d61d827e1" alt="Image 1" width="210" height="400"></td>
        <td><img src="https://github.com/vanntruongg/CT274-Programming-For-Mobile-Devices/assets/103330978/e0d963c6-b794-4f48-8339-bda7be9582de" alt="Image 2" width="210" height="400"></td>
        <td><img src="https://github.com/vanntruongg/CT274-Programming-For-Mobile-Devices/assets/103330978/420aabe6-1f10-4972-8230-de3ef0a0c27b" alt="Image 3" width="210" height="400"></td>
    </tr>
  <tr>
        <td><img src="https://github.com/vanntruongg/CT274-Programming-For-Mobile-Devices/assets/103330978/fd5b0d3f-1973-42a6-baef-1bb09f9b8b8d" alt="Image 4" width="210" height="400"></td>
    </tr>
</table>

- Project Configuration:

    Add the following dependencies to the app module's gradle file:"
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

**5. ...**