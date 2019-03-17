# CategoryChallenge


 I tried to use only things from Android Framework, except two things:
 - Retrofit + GSON -> can be easily replaced with HttpUrlConnection + manual json parsing
 - Glide -> it can be a separate challenge to write proper image loading + caching

 In this project I used MVVM approach with ViewModel from google architecture components.
 
 As a replacement for Dagger2, which I usually use, I implemented my own DI, class App has a role
 of dependency providers, it injects to ViewModelFactory and it provides ViewModels with all dependencies
 inside to views.
 
 As a replacement to RxJava, which I usually use, I am using Executor with single thread.
 
 As for database, I decided to use ContentProvider as you asked to use more from Android Framework,
 as a database manager. It helps to manage database without need to write all queries manually. To
 avoid security leaks, exported is set to false. I moved database to separate module, as it
 mentioned in task.
 Also, proguard is set up and tested to go.

 I made few layers, domain, data and presentation with DTOs, entities, mappers, use-cases to communicate between
 them. It makes code more flexible, scalable and readable.