package finki.mips.moviesearchapp.movie.room

import finki.mips.moviesearchapp.movie.LocalMovieDataSource
import finki.mips.moviesearchapp.movie.model.Movie

class RoomMovieDataSource(private val movieDao: MovieDao) : LocalMovieDataSource {

    override suspend fun insert(movie: Movie) =
        movieDao.insertMovie(movie)

    override suspend fun insertAll(movies: List<Movie>) =
        movies.forEach { movie ->
            movieDao.insertMovie(movie)
        }

    override suspend fun getMovies(): List<Movie> =
       movieDao.getMovies()

    override suspend fun getMovie(imdbID: String): Movie =
        movieDao.getMovie(imdbID)

    override suspend fun searchMovies(search: String): List<Movie> =
        movieDao.searchMovies(search)
}