package finki.mips.moviesearchapp.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import finki.mips.moviesearchapp.movie.network.MovieDbApiProvider
import finki.mips.moviesearchapp.movie.network.RetrofitMovieDataSource
import finki.mips.moviesearchapp.movie.repository.MovieRepository
import finki.mips.moviesearchapp.movie.room.MovieDatabase
import finki.mips.moviesearchapp.movie.room.RoomMovieDataSource
import finki.mips.moviesearchapp.utils.NetworkConnectivity

class MoviesViewModelsFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRepository::class.java)
            .newInstance(
                MovieRepository(
                    RetrofitMovieDataSource(MovieDbApiProvider.getMovieDbApi()),
                    RoomMovieDataSource(MovieDatabase.getDatabase(context).movieDao()),
                    NetworkConnectivity(context)
                )
            )
    }
}