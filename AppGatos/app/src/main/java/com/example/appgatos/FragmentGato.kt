package com.example.appgatos


import android.annotation.SuppressLint
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Filter
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.appgatos.adapter.GatoAdapter
import com.example.appgatos.databinding.FragmentGatoBinding
import com.example.appgatos.dataclass.Gato
import com.example.appgatos.tablayout.info_fragment
import com.example.appgatos.tablayout.stats_fragment
import com.google.android.material.tabs.TabLayout


class FragmentGato : Fragment() {

    private lateinit var binding : FragmentGatoBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentGatoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        //Declaración de variables
        val nav = findNavController()
        val gatico: Gato? =arguments?.getParcelable("gato")
        val url = gatico?.wikipediaUrl
        val intent = Intent(Intent.ACTION_VIEW )


        //Verificación de contenido no Null
        if (gatico != null) {
            binding.toolbar.title = gatico.name
            Glide.with(this).load(gatico.image?.url).into(binding.IgGto)
        }

        //Declaracion del icono de navegación
        binding.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)

        //Listener para navegar al fragment principal
        binding.toolbar.setOnClickListener {
           nav.navigate(R.id.fragmentLista)

       }

        //Listener para abrir el gato seleccionado en Wikipedia
        binding.fabWiki.setOnClickListener{
            intent.data = Uri.parse(url)
            startActivity(intent)
        }

        //Navegación del TabLayout
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val id = tab!!.position
                navegarFragm(id )
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })

        navegarFragm(0 )

    }


    //función para moverse entre los fragments
    private fun navegarFragm(itemId: Int){
        val fragment : Fragment
        fragment = when(itemId){
            1 -> stats_fragment()
            else -> info_fragment()
        }
        val fm = activity?.supportFragmentManager
        val transaction = fm?.beginTransaction()
        transaction?.replace(R.id.framelayout, fragment)
        transaction?.commit()

    }



}