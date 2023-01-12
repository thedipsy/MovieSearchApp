package finki.mips.moviesearchapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import finki.mips.moviesearchapp.R
import finki.mips.moviesearchapp.movie.model.Movie

class MovieAdapter(
    private val movies: ArrayList<Movie> = ArrayList(),
    val onClickListener: (imdbID: String) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MoviesViewHolder(view)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val item = movies[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { onClickListener(item.imdbID) }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateMovies(movies: List<Movie>) {
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    class MoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var ivMoviePoster: ImageView = view.findViewById(R.id.ivMoviePoster)
        private var tvMovieTitle: TextView = view.findViewById(R.id.tvMovieTitle)

        fun bind(movie: Movie) {
            Glide.with(ivMoviePoster)
                .load(movie.poster)
                .centerCrop().placeholder(R.drawable.ic_movie).into(ivMoviePoster)
            tvMovieTitle.text = movie.title
        }
    }
}