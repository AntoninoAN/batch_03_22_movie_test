package com.example.movietest.view

import com.example.movietest.model.MovieList

interface IViewList {
    fun displayData(dataSet: MovieList)
    fun getDataSet()
    fun bindPresenter()
    fun displayError(errorMessage: String)
}