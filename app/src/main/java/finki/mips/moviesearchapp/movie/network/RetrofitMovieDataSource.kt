package finki.mips.moviesearchapp.movie.network

import finki.mips.moviesearchapp.movie.RemoteMovieDataSource
import finki.mips.moviesearchapp.movie.model.Movie
import java.lang.Exception

class RetrofitMovieDataSource(private val movieDbApi: MovieDbApi) : RemoteMovieDataSource {

    override suspend fun searchMovies(search: String): List<Movie> {
        val movieResponse = movieDbApi.searchMovies(search)
        val responseBody = movieResponse.body()
        if (movieResponse.isSuccessful && responseBody != null) {
            return responseBody.result
        }
        throw Exception(movieResponse.message())
    }

    override suspend fun getMovie(imdbID: String): Movie {
        val movieResponse = movieDbApi.getMovie(imdbID)
        val responseBody = movieResponse.body()
        if (movieResponse.isSuccessful && responseBody != null) {
            return responseBody
        }
        throw Exception(movieResponse.message())
    }
}