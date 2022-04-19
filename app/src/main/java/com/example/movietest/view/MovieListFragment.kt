package com.example.movietest.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movietest.R
import com.example.movietest.model.MovieList
import com.example.movietest.model.MovieResponse
import com.example.movietest.model.remote.MoviesService
import com.example.movietest.presenter.PresenterList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "MovieListFragment"

class MovieListFragment: Fragment(), IViewList {

    private lateinit var movieList: RecyclerView
    private lateinit var adapter: MoviesAdapter
    private lateinit var presenter: PresenterList

    override fun onAttach(context: Context) {
        super.onAttach(context)
        bindPresenter()
    }

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
        //getMovies()
        return view
    }

    override fun onViewCreated(view: View,
                               savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataSet()
    }

    private fun initViews(view: View) {
        movieList = view.findViewById(R.id.movie_list)
        movieList.layoutManager = GridLayoutManager(context, 3)
    }

    private fun getMovies(){

    }

    private fun FragmentActivity.openMovieDetail(movieDetail: MovieResponse){
        supportFragmentManager.beginTransaction()
            .replace(android.R.id.content, MovieDetailsFragment.newInstance(movieDetail))
            .addToBackStack(null)
            .commit()
    }

    override fun displayData(dataSet: MovieList) {
        adapter = MoviesAdapter(dataSet) { movieDetail->
            activity?.openMovieDetail(movieDetail)

            // invoke the DetailsFragment
            // invoke the PresenterDetails

        }
        movieList.adapter = adapter
    }

    override fun getDataSet() {
        presenter.getData()
    }

    override fun bindPresenter() {
        presenter = PresenterList()
        presenter.bind(this)
    }

    override fun displayError(errorMessage: String) {
        Toast.makeText(context,
            errorMessage,
            Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        presenter.onDestroy()
    }
}