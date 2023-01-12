package finki.mips.moviesearchapp.movie

import finki.mips.moviesearchapp.movie.model.Movie

interface RemoteMovieDataSource {
    suspend fun searchMovies(search: String) : List<Movie>?
    suspend fun getMovie(imdbID: String) : Movie
}