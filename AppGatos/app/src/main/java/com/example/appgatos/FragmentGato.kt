package com.example.appgatos


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.view.ViewTreeObserver.OnScrollChangedListener
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.appgatos.databinding.FragmentGatoBinding
import com.example.appgatos.dataclass.EnvioVoto
import com.example.appgatos.dataclass.Gato
import com.example.appgatos.retrofit.Repositorio
import com.example.appgatos.tablayout.info_fragment
import com.example.appgatos.tablayout.stats_fragment
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FragmentGato : Fragment() {

    private lateinit var binding : FragmentGatoBinding
    private lateinit var listavotos : EnvioVoto


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

        //Envio voto positivo
        binding.fabUp.setOnClickListener(){
            val repositorio = Repositorio()

            listavotos = EnvioVoto(gatico?.image?.id, "Victor",1)

            CoroutineScope(Dispatchers.IO).launch {
                val respuesta = repositorio.enviarVoto(listavotos)

                withContext(Dispatchers.Main){
                    if(respuesta.isSuccessful && respuesta.code() == 200) {
                        val miRespuesta = respuesta.body()
                        val lista_votos = miRespuesta
                        lista_votos?.let {
                            Toast.makeText(context, "El voto se ha enviado" + " " + miRespuesta.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                    else{
                        val error = respuesta.message()
                        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }


        //Envio voto negativo
        binding.fabDown.setOnClickListener(){
            val repositorio = Repositorio()

            listavotos = EnvioVoto(gatico?.image?.id, "Victor",0)

            CoroutineScope(Dispatchers.IO).launch {
                val respuesta = repositorio.enviarVoto(listavotos)

                withContext(Dispatchers.Main){
                    if(respuesta.isSuccessful && respuesta.code() == 200) {
                        val miRespuesta = respuesta.body()
                        val lista_votos = miRespuesta
                        lista_votos?.let {
                            Toast.makeText(context, miRespuesta.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                    else{
                        val error = respuesta.message()
                        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }


        //Navegación del TabLayout
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val id = tab!!.position
                navegarFragm(id, gatico!!)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })

        navegarFragm(0, gatico!! )

        //Navegación ocultando fabs
        binding.nsd.viewTreeObserver.addOnScrollChangedListener(OnScrollChangedListener {
            val scrollY: Int = binding.nsd.scrollY //for verticalScrollView
            //DO SOMETHING WITH THE SCROLL COORDINATES
            if (scrollY > 0 || scrollY < 0) {
                binding.fabDown.hide()
                binding.fabUp.hide()
            }
            else{
                binding.fabDown.show()
                binding.fabUp.show()
            }
        })
    }


    //función para moverse entre los fragments
    private fun navegarFragm(itemId: Int , gato: Gato){
        val fragment : Fragment
        fragment = when(itemId){
            1 -> stats_fragment()
            else -> info_fragment()
        }
        val bundle = bundleOf("gato" to gato)
        val fm = activity?.supportFragmentManager
        val transaction = fm?.beginTransaction()
        transaction?.replace(R.id.framelayout, fragment)
        fragment.arguments = bundle
        transaction?.commit()

    }



}