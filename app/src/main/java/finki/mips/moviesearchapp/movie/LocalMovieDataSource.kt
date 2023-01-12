package finki.mips.moviesearchapp.movie

import finki.mips.moviesearchapp.movie.model.Movie

interface LocalMovieDataSource {
    suspend fun insert(movie: Movie)
    suspend fun insertAll(movies: List<Movie>)
    suspend fun getMovie(imdbID: String) : Movie
    suspend fun getMovies() : List<Movie>
    suspend fun searchMovies(search: String) : List<Movie>
}