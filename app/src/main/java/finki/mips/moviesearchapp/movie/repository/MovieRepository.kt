package finki.mips.moviesearchapp.movie.repository

import finki.mips.moviesearchapp.movie.LocalMovieDataSource
import finki.mips.moviesearchapp.movie.RemoteMovieDataSource
import finki.mips.moviesearchapp.movie.model.Movie
import finki.mips.moviesearchapp.utils.NetworkConnectivity

class MovieRepository(
    private val remoteMovieDataSource: RemoteMovieDataSource,
    private val localMovieDataSource: LocalMovieDataSource,
    private val networkConnectivity: NetworkConnectivity
) {

    suspend fun searchMovie(search: String): List<Movie> {
        return if (networkConnectivity.isNetworkAvailable) {
           remoteMovieDataSource.searchMovies(search)?.let {  movies ->
               localMovieDataSource.insertAll(movies)
               movies
           } ?: emptyList()
        } else {
            localMovieDataSource.searchMovies(search)
        }
    }

    suspend fun getMovie(imdbID: String): Movie = remoteMovieDataSource.getMovie(imdbID)

    suspend fun listMovies(): List<Movie> = localMovieDataSource.getMovies()

}