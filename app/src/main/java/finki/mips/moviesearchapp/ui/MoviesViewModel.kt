package finki.mips.moviesearchapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import finki.mips.moviesearchapp.movie.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import finki.mips.moviesearchapp.movie.repository.MovieRepository

class MoviesViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies : LiveData<List<Movie>> = _movies

    private val _selectedMovie = MutableLiveData<Movie>()
    val selectedMovie : LiveData<Movie> = _selectedMovie

    fun searchMovie(search: String) {
        if(search.isEmpty()){
            listAll()
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            val movies = movieRepository.searchMovie(search)
            _movies.postValue(movies)
        }
    }

    fun getMovie(imdbID: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val movie = movieRepository.getMovie(imdbID)
            _selectedMovie.postValue(movie)
        }
    }

    fun listAll() {
        viewModelScope.launch(Dispatchers.IO) {
            val movies = movieRepository.listMovies()
            _movies.postValue(movies)
        }
    }
}