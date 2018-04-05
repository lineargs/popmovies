# Popular Movies Stage 2

## Project Overview
This project is part of the Android Developer Nanodegree on **Udacity**.

## Criteria met:
- UI contains an element (Settings menu) to toggle the sort order of the movies by: most popular, highest rated.
- Movies are displayed in the main layout via a grid of their corresponding movie poster thumbnails.
- UI contains a screen for displaying the details for a selected movie.
- Movie Details layout contains title, release date, movie poster, vote average, and plot synopsis.
- Movie Details layout contains a section for displaying trailer videos and user reviews.
- When a user changes the sort criteria (most popular, highest rated, and favorites) the main view gets updated correctly.
- When a movie poster thumbnail is selected, the movie details screen is launched.
- When a trailer is selected, app uses an Intent to launch the trailer.
- In the movies detail screen, a user can tap a button(a star) to mark it as a Favorite.
- In a background thread, app queries the /movie/popular or /movie/top_rated API for the sort criteria specified in the settings menu.
- App requests for related videos for a selected movie via the /movie/{id}/videos endpoint in a background thread and displays those details when the user selects a movie.
- App requests for user reviews for a selected movie via the /movie/{id}/reviews endpoint in a background thread and displays those details when the user selects a movie.
- The titles and IDs of the user’s favorite movies are stored in a native SQLite database and are exposed via a ContentProvider. This ContentProvider is updated whenever the user favorites or unfavorites a movie. No other persistence libraries are used.
- When the "favorites" setting option is selected, the main view displays the entire favorites collection based on movie ids stored in the ContentProvider.
- ContentProvider stores the movie poster, synopsis, user rating, and release date, and display them even when offline.
- Implemented sharing functionality to allow the user to share the first trailer’s YouTube URL from the movie details screen.

## License

```This is free and unencumbered software released into the public domain.

Anyone is free to copy, modify, publish, use, compile, sell, or
distribute this software, either in source code form or as a compiled
binary, for any purpose, commercial or non-commercial, and by any
means.

In jurisdictions that recognize copyright laws, the author or authors
of this software dedicate any and all copyright interest in the
software to the public domain. We make this dedication for the benefit
of the public at large and to the detriment of our heirs and
successors. We intend this dedication to be an overt act of
relinquishment in perpetuity of all present and future rights to this
software under copyright law.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.

For more information, please refer to <http://unlicense.org>
