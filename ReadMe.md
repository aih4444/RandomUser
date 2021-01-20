## Built With 🛠
<p> Kotlin - First class and official programming language for Android development.</p>
<p>Coroutines - For concurrency and multithreading</p>
<p>Android Architecture Components - Collection of libraries that help you design robust, testable, and maintainable apps.</p>
<ul>
  <li>LiveData - Data objects that notify views when the underlying database changes.</li>
  <li>ViewModel - Stores UI-related data that it's survive configuration changes.</li>
  <li>Workmanager - It is for background work that's deferrable and requires guranteed execution.</li>
  <li>ViewBinding - Generates a binding class for each XML layout file present in that module and allows you to more easily write code that interacts with views.</li>
  <li>Room - SQLite object relational mapping library.</li>
  </ul>
<p>Koin - It's a lightweight dependency injection framework for kotlin.<p>
<p>Retrofit - A type-safe HTTP client for Android and Java.</p>
<p>GSON - Gson is a Java library that can be used to convert Java Objects into their JSON representation.<p>
<p>Glide - An image loading library for Android </p>
<p>SafeArgs - To pass data between fragments
<p>Material Components for Android - Modular and customizable Material Design UI components for Android.</p>
<p>Espresso - Espresso is a Testing Framework for Android to make it easy to write reliable user inteface tests.</p>
<p>Mockito - Mockito is a popular mocking library for clean and readable unit tests. 

## Architecture
<p>This app uses MVVM (Model View View-Model) architecture.</p>
Architecture Design

The Project follows a MVVM with Repository pattern architecture. This architecture was chosen for:

Seperation of Concerns that provides a way to testing the architecture components in isolation and allows for the View classes to be updated without modifying the ViewModel classes.
Resilience to configuration changes allows the ViewModel classes to store UI data that would otherwise be lost on screen rotation or activity lifecycle changes.
Communication between fragments using a ViewModel class removes the need for fragments to communicate via an Activity using callbacks.
The View classes use data binding to communicate updates to their respective ViewModel classes. The ViewModel classes communicate with a Repository class using coroutines and receives responses using LiveData. This is then passed back to the View classes observing this LiveData. The Repository class communicates with a RESTful API using Retrofit and caches the response to a local Room database.
![68747470733a2f2f646576656c6f7065722e616e64726f69642e636f6d2f746f7069632f6c69627261726965732f6172636869746563747572652f696d616765732f66696e616c2d6172636869746563747572652e706e67](https://user-images.githubusercontent.com/58938625/91903352-ff443400-ec9a-11ea-8fd0-853d6336bcf4.png)
<img src = "https://i.imgur.com/fQi9Olw.jpg">
<img src = "https://i.imgur.com/7crT8sM.jpg">