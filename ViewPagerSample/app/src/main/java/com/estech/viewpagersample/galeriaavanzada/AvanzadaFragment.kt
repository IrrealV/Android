package com.estech.viewpagersample.galeriaavanzada

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.estech.viewpagersample.databinding.FragmentAvanzadaBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AvanzadaFragment : Fragment() {

    private lateinit var binding: FragmentAvanzadaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAvanzadaBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pelicula1 = Pelicula(
            "https://static.wikia.nocookie.net/doblaje/images/9/9a/Elpadrino.jpg/revision/latest?cb=20211023042804&path-prefix=es",
            "El Padrino"
        )
        val pelicula2 = Pelicula("https://images-na.ssl-images-amazon.com/images/I/91H6ueCBD1L.jpg", "La Lista de Schindler")
        val pelicula3 = Pelicula("https://pics.filmaffinity.com/Pulp_Fiction-210382116-large.jpg", "Pulp Fiction")
        val pelicula4 = Pelicula(
            "https://static.wikia.nocookie.net/tarantino/images/6/6a/Reservoir_Dogs.jpg/revision/latest?cb=20121229163618&path-prefix=es",
            "Reservoir Dogs"
        )
        val listaPelis = listOf(pelicula1, pelicula2, pelicula3, pelicula4)

        val adapter = AdapterAvanzado(listaPelis)
        binding.viewpager.adapter = adapter
    }
}