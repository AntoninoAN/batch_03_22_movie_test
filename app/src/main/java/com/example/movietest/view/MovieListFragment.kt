package com.example.movietest.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movietest.R
import com.example.movietest.model.MovieList
import com.example.movietest.model.MovieResponse
import com.example.movietest.model.remote.MoviesService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "MovieListFragment"

class MovieListFragment: Fragment() {

    private lateinit var movieList: RecyclerView
    private lateinit var adapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(
            R.layout.movie_list_fragment_layout,
            container,
            false
        )
        initViews(view)
        getMovies()
        return view
    }

    private fun initViews(view: View) {
        movieList = view.findViewById(R.id.movie_list)
        movieList.layoutManager = GridLayoutManager(context, 3)
    }

    private fun getMovies(){
        MoviesService.initRetrofit().getMovies()
            .enqueue(
                object : Callback<MovieList> {
                    override fun onResponse(
                        call: Call<MovieList>,
                        response: Response<MovieList>
                    ) {
                        Log.d(TAG, "onResponse: $response")
                        if (response.isSuccessful){
                            updateAdapter(response.body())
                        }else
                            showError(response.message())
                    }

                    override fun onFailure(call: Call<MovieList>, t: Throwable) {
                        Log.d(TAG, "onFailure: $t")
                        showError(t.message ?: "Unknown error")
                    }

                }
            )
    }

    private fun showError(errorMessage: String) {

    }

    private fun updateAdapter(body: MovieList?) {
        body?.let{
            adapter = MoviesAdapter(it) {movieDetail->
                activity?.openMovieDetail(movieDetail)
            }
            movieList.adapter = adapter
        } ?: showError("No response from server")
    }

    private fun FragmentActivity.openMovieDetail(movieDetail: MovieResponse){
        supportFragmentManager.beginTransaction()
            .replace(android.R.id.content, MovieDetailsFragment.newInstance(movieDetail))
            .addToBackStack(null)
            .commit()
    }
}