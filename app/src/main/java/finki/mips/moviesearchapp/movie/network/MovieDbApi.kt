package finki.mips.moviesearchapp.movie.network

import finki.mips.moviesearchapp.movie.model.Movie
import finki.mips.moviesearchapp.movie.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDbApi {
    @GET(".")
    suspend fun searchMovies(@Query("s") search: String, @Query("plot") plot: String = "short") : Response<MovieResponse>

    @GET(".")
    suspend fun getMovie(@Query("i") imdbID: String, @Query("plot") plot: String = "short") : Response<Movie>
}