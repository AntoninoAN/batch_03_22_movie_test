package com.example.movietest.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.movietest.R
import com.example.movietest.model.MovieResponse
import com.squareup.picasso.Picasso

class MovieDetailsFragment: Fragment() {
    companion object{
        private const val MOVIE_DETAIL_DATA = "MOVIE_DETAIL_DATA"

        fun newInstance(movieDetail: MovieResponse) =
            MovieDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(MOVIE_DETAIL_DATA, movieDetail)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(
            R.layout.movie_detail_fragment_layout,
            container,
            false
        )

        arguments?.getParcelable<MovieResponse>(MOVIE_DETAIL_DATA)?.let {
            initViews(view, it)
        }

        return view
    }

    private fun initViews(parent: View, movieDetail: MovieResponse) {
        parent.run {
            findViewById<TextView>(R.id.tv_movie_detail_genre).text = movieDetail.genre.toString()
            findViewById<TextView>(R.id.tv_movie_detail_title).text = movieDetail.title
            findViewById<TextView>(R.id.tv_movie_detail_release).text = movieDetail.releaseYear.toString()
            findViewById<RatingBar>(R.id.rb_movie_detail_rating).rating = movieDetail.rating
            Picasso.get().load(movieDetail.image).into(findViewById<ImageView>(R.id.iv_movie_detail_poster))
        }
    }
}