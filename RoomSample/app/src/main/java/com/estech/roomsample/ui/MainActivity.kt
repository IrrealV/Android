package com.estech.roomsample.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.estech.roomsample.MyApplication
import com.estech.roomsample.databinding.ActivityMainBinding
import com.estech.roomsample.room.MovieEntity
import com.estech.roomsample.viewmodel.MovieViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MovieAdapter
    private val vm: MovieViewModel by viewModels {
        MovieViewModel.MyViewModelFactory((application as MyApplication).repository)
    }
    private val TAG = "TAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configRecycler()

        binding.buttonAdd.setOnClickListener { addMovie() }

        vm.allMovies.observe(this) { items ->
            adapter.updateUserList(items as ArrayList<MovieEntity>)
        }
    }

    private fun configRecycler() {
        val layoutManager = LinearLayoutManager(this)
        adapter = MovieAdapter()
        binding.recycler.layoutManager = layoutManager
        binding.recycler.adapter = adapter
    }

    private fun addMovie() {
        val name = binding.editTextTextMovie.text.toString()
        val year = binding.editTextYear.text.toString()
        if (name.isNotEmpty() && year.isNotEmpty()) {
            val movie = MovieEntity(name, year.toInt())
            vm.insert(movie)
        } else
            Toast.makeText(this, "Falta información", Toast.LENGTH_SHORT).show()

    }

}