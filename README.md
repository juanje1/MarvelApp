# MarvelApp
Application for showing a Marvel characters list and the details of each one

## Description
In the main activity the app shows a Marvel characters list by default with '25' elements and sorting by 'Name Ascending'. In this activity, it shows a character picture, the character name and the number of aparitions in 'Comics', 'Stories', 'Events' and 'Series'.

When you select one, you can see the details with a picture, the name, the description, and maximum 25 'Comics', 25 'Stories', 25 'Events' and 25 'Series'.

You can go to 'Settings' in the 'Menu' and change the limit of the Marvel characters list (between 1 to 100) and 4 modes of sorting ('Name Ascending', 'Name Descending', 'Modified Ascending' and 'Modified Descending'). When you go back, you can save or discard the changes.

The MainActivity loads the list when the application is created and when the settings are updated (not when you come back of details).

Also, there are error control (code 409 and others) showing a SnackBar with the error. You can retry until 3 times, and after of that you can not try it again (but yes to modify the settings).

Finally, it has the following characteristics:

- Full code in Kotlin
- Request to a Marvel API
- Anko Library
- Picasso Library (for images)
- Gson Library (for deserialization)
- Lambdas
- Extension Functions
- Application Singleton
- Delegation
- Generics
- Shared Preferences
- Coroutines
- Unit Testing
- Instrumentation Testing
- Views: RecyclerView, ImageView, TextView, AlertDialog, SnackBar
