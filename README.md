<h1 align="center"> MVVM Template with Cats :cat: </h1>
<p align="center">
<img src="https://img.shields.io/badge/platform-android-green.svg" />
<img src="https://img.shields.io/badge/language-kotlin-orange.svg" />
<img src="https://img.shields.io/badge/License-GPL%20v3-blue.svg" />
</p>

## Preview
![Preview](http://metautbenjamin.com:3000/file/image/MVVM1.png)


## Overview
This Repository contains a simple MVVM Architecture using TheCatApi made in Kotlin without RX and without dagger2/Koin
Just the basic :wink:

### Packages

 - **BasicApp class:**  Override the Application() class to inject dependencies ( like repositories )
 
 - **data:**  Contains local entities, DTO's, Api services, repositories and also the NetworkProvider
 
 - **ui:** Just contain View Classes like Activities/Fragment, Adapters, etc...
 
 - **utils:**  Contain tools and extensions ( like a BindingExtension to use Glide directly into a layout.xml )
 
 - **viewmodels:**  Contain all the ViewModels classes provinding views with data
 
 ## Model View ViewModel 
 This project is basically a very simple representation of the MVVM ( without any optional library like Dagger2/Koin or RXJava/RXKotlin )
 and this is how it's implemented
 
- **Repository ( Models )**
  - >Fetch data from DB/API
  
  - >Return LiveData 
  
- **ViewModel**  
  - >Expose models get from the repository to the view
  
  - >Can be create with a factory to allow the injection of dependencies

- **View ( ui )** 
  - >Subscribe to the ViewModel
  
  - >Observe data exposed by the ViewModel
  
  - >Update the UI when ViewModel data's change
  
## Some librairies used 
In fact I used some librairies to make this simple project but don't worry these librairies can be used in other architecture
( MVP, MVC, etc )

The project use AndroidX

- [Glide](https://github.com/bumptech/glide) media management and image loading framework for Android

- [Retrofit2](https://github.com/square/retrofit) HTTP client for Android

- [Retrofit LiveData Adapter](https://github.com/leonardoxh/livedata-call-adapter) A Retrofit 2 CallAdapter.Factory for Android LiveData

- [Retrofit Converter Moshi](https://github.com/square/retrofit/tree/master/retrofit-converters/moshi) A Converter which uses Moshi for serialization to and from JSON.

- [Moshi](https://github.com/square/moshi) Moshi is a modern JSON library for Android and Java. It makes it easy to parse JSON into Java objects

If you have any trouble of understanding or if you want to help me to improve myself let me know ! :blush:
