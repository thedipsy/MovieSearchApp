package finki.mips.moviesearchapp.movie.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Movie(
    @PrimaryKey
    val imdbID: String,
    @SerializedName("Title")
    val title: String,
    @SerializedName("Poster")
    val poster: String,
    @SerializedName("Plot")
    val plot: String ?= null
)