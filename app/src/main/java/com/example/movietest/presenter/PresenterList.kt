package com.example.movietest.presenter

import android.util.Log
import com.example.movietest.model.MovieList
import com.example.movietest.model.remote.MoviesService
import com.example.movietest.view.IViewList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "PresenterList"

class PresenterList: IPresenterList {

    private var view: IViewList? = null

    override fun bind(view: IViewList) {
        this.view = view
    }

    override fun getData() {
        MoviesService.initRetrofit().getMovies()
            .enqueue(
                object : Callback<MovieList> {
                    override fun onResponse(
                        call: Call<MovieList>,
                        response: Response<MovieList>
                    ) {
                        Log.d(TAG, "onResponse: $response")
                        if (response.isSuccessful){
                            response.body()?.let {
                                displayData(it)
                            } ?: displayError("Empty body")
                        }else
                            displayError(response.message())
                    }

                    override fun onFailure(call: Call<MovieList>, t: Throwable) {
                        Log.d(TAG, "onFailure: $t")
                        displayError(t.message ?: "Unknown error")
                    }

                }
            )
    }

    override fun displayData(dataSet: MovieList) {
        view?.displayData(dataSet)
    }

    override fun displayError(errorMessage: String) {
        view?.displayError(errorMessage)
    }

    override fun onDestroy() {
        view = null
    }
}