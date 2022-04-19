package com.example.movietest.presenter

import com.example.movietest.model.MovieResponse
import com.example.movietest.view.IViewDetails

interface IPresenterDetails {
    fun bind(view: IViewDetails)
    fun onDestroy()
    fun displayData(movieDetail: MovieResponse)

}