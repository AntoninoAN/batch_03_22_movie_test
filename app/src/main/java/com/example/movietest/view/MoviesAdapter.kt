package com.example.movietest.view


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movietest.R
import com.example.movietest.model.MovieList
import com.example.movietest.model.MovieResponse
import com.squareup.picasso.Picasso

class MoviesAdapter(private val dataSet: MovieList,
                    private val openDetails: (MovieResponse) -> Unit) :
    RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    class MoviesViewHolder(private val view: View) :
        RecyclerView.ViewHolder(view){
        private val moviePoster: ImageView =
            view.findViewById(R.id.iv_movie_poster)
        private val movieTitle : TextView =
            view.findViewById(R.id.tv_movie_title)

        fun onBind(dataItem: MovieResponse,
        openDetails: (MovieResponse) -> Unit){
            movieTitle.text = dataItem.title
            Picasso.get().load(dataItem.image).into(moviePoster)
            view.setOnClickListener { openDetails(dataItem) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.movies_item_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.onBind(dataSet[position], openDetails)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}