package com.example.movietest.presenter

import com.example.movietest.model.MovieList
import com.example.movietest.view.IViewList

interface IPresenterList {
    fun bind(view: IViewList)
    fun getData()
    fun displayData(dataSet: MovieList)
    fun displayError(errorMessage: String)
    fun onDestroy()// Dispose in Configuration changes
}