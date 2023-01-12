package finki.mips.moviesearchapp.movie.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("Search") val result: List<Movie>
)