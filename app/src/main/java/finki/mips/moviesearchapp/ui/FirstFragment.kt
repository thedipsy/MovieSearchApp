package finki.mips.moviesearchapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import finki.mips.moviesearchapp.R
import finki.mips.moviesearchapp.adapters.MovieAdapter
import finki.mips.moviesearchapp.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    companion object {
        private const val MOVIE_ID = "movie_id"
    }

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private var moviesViewModel: MoviesViewModel? = null
    private var adapter: MovieAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setup()
        setupViewModel()
        observeData()
        init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setup() {
        adapter = MovieAdapter { imdbID ->
            val bundle = Bundle().apply { putString(MOVIE_ID, imdbID) }
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
        }
        binding.recyclerView.adapter = adapter

        binding.etSearch.setOnEditorActionListener { view, action, _ ->
            if(action == EditorInfo.IME_ACTION_NEXT) {
                moviesViewModel?.let {
                    it.searchMovie(view.text.toString())
                    return@setOnEditorActionListener true
                }
            }
            return@setOnEditorActionListener false
        }
    }

    private fun setupViewModel() {
        val viewModelFactory = MoviesViewModelsFactory(requireContext())
        moviesViewModel = ViewModelProvider(this, viewModelFactory)[MoviesViewModel::class.java]
    }

    private fun observeData() {
        moviesViewModel?.movies?.observe(viewLifecycleOwner) {
            adapter?.updateMovies(it)
        }
    }

    private fun init() = moviesViewModel?.listAll()
}