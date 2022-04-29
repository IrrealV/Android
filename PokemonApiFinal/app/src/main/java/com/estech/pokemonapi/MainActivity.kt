package com.estech.pokemonapi

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.estech.pokemonapi.databinding.ActivityMainBinding
import com.estech.pokemonapi.models.Resultado
import com.estech.retrofitsample.retrofit.Repositorio
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: DataAdapter
    private var pullToRefreshWorking = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getPokemonFromApi()

        binding.swipe.setOnRefreshListener {
            binding.swipe.isRefreshing = true
            pullToRefreshWorking = true
            getPokemonFromApi()
        }

        binding.swipe.setColorSchemeColors(Color.MAGENTA, Color.YELLOW)
        binding.swipe.setColorSchemeResources(R.color.purple_200, R.color.teal_200)
        binding.swipe.setProgressBackgroundColorSchemeColor(Color.TRANSPARENT)
//        binding.swipe.setProgressBackgroundColorSchemeResource(R.color.black)
        binding.swipe.setSize(SwipeRefreshLayout.LARGE)

        binding.searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.filter.filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }
        })
        binding.searchview.setOnCloseListener {
            adapter.filter.filter("")
            true
        }
    }

    private fun getPokemonFromApi() {
        val miRepositorio = Repositorio()

        CoroutineScope(Dispatchers.IO).launch {
            val response = miRepositorio.getPokemons()

            withContext(Dispatchers.Main) {


                if (response.isSuccessful) {
                    binding.swipe.isRefreshing = false
                    val respuesta = response.body()
                    val listaPokemon = respuesta?.data
                    listaPokemon?.let {

                        if (pullToRefreshWorking) {
                            pullToRefreshWorking = false
                            refreshRecycler(it)
                        } else {
                            configRecycler(it)
                        }
                    }
                } else {
                    binding.swipe.isRefreshing = false
                    Toast.makeText(
                        this@MainActivity,
                        "Error: ${response.message()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun configRecycler(listaPersonajes: List<Resultado.Data>) {
        val recyclerView = binding.recyclerview
        adapter = DataAdapter(listaPersonajes as ArrayList<Resultado.Data>)
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    private fun refreshRecycler(listaPersonajes: List<Resultado.Data>) {
        adapter.refreshList(listaPersonajes as ArrayList<Resultado.Data>)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val item = menu?.findItem(R.id.app_bar_search)
        val searchView = item?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.filter.filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }

        })
        return true
    }
}