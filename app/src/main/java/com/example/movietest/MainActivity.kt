package com.example.movietest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movietest.model.MovieList
import com.example.movietest.model.MovieResponse
import com.example.movietest.view.MovieListFragment
import com.example.movietest.view.MoviesAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MovieListFragment())
            .commit()
    }
}