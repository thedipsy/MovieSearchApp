package finki.mips.moviesearchapp.movie.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import finki.mips.moviesearchapp.movie.model.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movies: Movie)

    @Query("SELECT * FROM movie WHERE imdbID = :imdbID")
    suspend fun getMovie(imdbID: String) : Movie

    @Query("SELECT * FROM movie")
    suspend fun getMovies() : List<Movie>

    @Query("SELECT * FROM movie WHERE title LIKE :search")
    suspend fun searchMovies(search: String) : List<Movie>

}