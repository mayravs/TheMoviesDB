# TheMoviesDB 🍿
> A modern Android application that showcases the latest "Now Playing" movies by fetching data from The Movie Database (TMDB) API. It follows a clean architecture approach and demonstrates a robust, offline-first user experience.

## 🛠 Tech Stack
*   **Languages:** [Kotlin](https://kotlinlang.org/)
*   **Frameworks/Libraries:** [Retrofit](https://square.github.io/retrofit/), [Hilt](https://dagger.dev/hilt/), [Room](https://developer.android.com/training/data-storage/room?authuser=1), [Jetpack Compose](https://developer.android.com/jetpack/compose)
*   **Architecture:** MVVM, Clean Architecture
*   **API:** [The Movie Database API](https://developers.themoviedb.org/3/getting-started/introduction)

## 📸 Demo
<video src="https://github.com/mayravs/TheMoviesDB/main/demo.webm" controls="controls" style="max-width: 100%;"></video><br>

## ✨ Key Features
*   **Movie Discovery:** Browse a list of [currently playing movies](https://developers.themoviedb.org/3/movies/get-now-playing) with high-quality posters and brief overviews.
*   **Detailed Insights:** View comprehensive details for specific movies, including full overviews, backdrop imagery, release dates, and a visual star rating system.
*   **Offline Support:** Seamlessly view previously loaded movies even without an internet connection, thanks to local caching.
*   **Responsive UI:** Fully adaptive layouts that support both Portrait and Landscape orientations with proper scrolling behavior.

## ⚙️ Technical Highlights
*   **Modern UI Stack:** Built entirely with Jetpack Compose for a declarative UI, utilizing Material 3 components and dynamic theming.
*   **Clean Architecture:** Organizes code into distinct layers (Data, Domain, UI) to ensure the codebase is maintainable, testable, and scalable.
*   **Reactive Data Flow:** Uses Kotlin Coroutines and Flow to handle asynchronous data streams and state management (via StateFlow).
*   **Dependency Injection:** Implements Hilt (Dagger) for standardizing dependency injection across the application.
*   **Local Persistence:** Employs Room Database as a local cache to implement an "offline-first" architecture.
*   **Networking & Image Loading:** Uses Retrofit for type-safe API communication and Coil for efficient, lifecycle-aware image loading and caching.
*   **Type-Safe Navigation:** Utilizes the latest Jetpack Navigation Compose with type-safe arguments for moving between screens.

## 🚀 Getting Started
### Prerequisites
*   Android Studio
*   API Key from [The Movie Database API](https://developers.themoviedb.org/3/getting-started/introduction)

### Setup
1. Clone the repository: `git clone https://github.com/mayravs/TheMoviesDB.git`
2. Open in Android Studio
3. Sync dependencies (Gradle)
4. Run the application
