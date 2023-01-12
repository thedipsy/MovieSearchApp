package finki.mips.moviesearchapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import finki.mips.moviesearchapp.R
import finki.mips.moviesearchapp.databinding.FragmentSecondBinding
import finki.mips.moviesearchapp.movie.model.Movie

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 * Representing details of the selected movie.
 */
class SecondFragment : Fragment() {

    companion object {
        private const val MOVIE_ID = "movie_id"
    }

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    private var moviesViewModel: MoviesViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        init()
        observeData()

    }

    private fun setupViewModel() {
        val viewModelFactory = MoviesViewModelsFactory(requireContext())
        moviesViewModel = ViewModelProvider(this, viewModelFactory)[MoviesViewModel::class.java]
    }

    private fun observeData() {
        moviesViewModel?.selectedMovie?.observe(viewLifecycleOwner) { movie ->
            updateUi(movie)
        }
    }

    private fun updateUi(movie: Movie) =
        binding.apply {
            tvMovieTitle.text = movie.title
            tvMoviePlot.text = movie.plot
            Glide.with(ivMoviePoster)
                .load(movie.poster)
                .centerCrop().placeholder(R.drawable.ic_movie).into(ivMoviePoster)
        }


    private fun init() {
        arguments?.getString(MOVIE_ID)?.let {
            moviesViewModel?.getMovie(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}