package com.example.appgatos


import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.appgatos.adapter.GatoAdapter
import com.example.appgatos.databinding.ListaFragmentBinding
import com.example.appgatos.dataclass.Gato
import com.example.appgatos.retrofit.Repositorio
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentLista : Fragment() {

    private lateinit var binding: ListaFragmentBinding
    private lateinit var adapter: GatoAdapter
    private var pullToRefreshWorking = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = ListaFragmentBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Establece el titulo al string introducido e infla el menú
        binding.toolbar.title = "Gaticos y sus razas"
        binding.toolbar.inflateMenu(R.menu.menu)


        //Declaración de variables
        val nav = findNavController()
        val itemBuscar = binding.toolbar.menu.findItem(R.id.app_bar_search)
        val searchView = itemBuscar.actionView as SearchView

        //Crea el gato
        crearGato()

        //Click listeners
        binding.fab.setOnClickListener{
            nav.navigate(R.id.action_fragmentLista_to_fragmentVoto)
        }





        //Refresh de la lista de gatos
        binding.swipe.setOnRefreshListener {
            binding.swipe.isRefreshing = true
            pullToRefreshWorking = true
            crearGato()
        }

        //Estilos del refresh
        binding.swipe.setColorSchemeColors(Color.MAGENTA, Color.YELLOW)
        binding.swipe.setColorSchemeResources(R.color.purple_200, R.color.teal_200)
        binding.swipe.setSize(SwipeRefreshLayout.LARGE)


        //Cuando se hace scroll en el recicler el fab se oculta, en cambio cuando se detiene se muestra
        binding.recicler.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0 || dy < 0) {
                    binding.fab.hide()
                }
            }
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                binding.fab.show()
            }
        })



        //Recibe lo introducido por texto en el buscador y filtra la lista
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

    }


    private fun configRecycler(listGato: List<Gato>){
        val reciclerView = binding.recicler
        adapter = GatoAdapter(listGato as ArrayList<Gato>)
        val layoutManager =  LinearLayoutManager(reciclerView.context)
        reciclerView.layoutManager = layoutManager
        reciclerView.adapter = adapter
    }


    private fun crearGato(){
        val repo = Repositorio()

        //Introduce la lista recibida de la api por el recicler
        CoroutineScope(Dispatchers.IO).launch {
            val gatos = repo.todosLosGatos()

            withContext(Dispatchers.Main){
                if(gatos.isSuccessful){
                    val listGatos = gatos.body()
                    listGatos?.let {
                        if (pullToRefreshWorking) {
                            pullToRefreshWorking = false
                            refreshRecycler(it)
                        } else {
                            configRecycler(it)
                        }
                    }
                }
            }

        }
    }

    private fun refreshRecycler(listaGatos: ArrayList<Gato>) {
        adapter.refreshList(listaGatos )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        binding.toolbar.inflateMenu(R.menu.menu)
        val itemOrdHead = menu.findItem(R.id.filterUp)
        val itemOrdTail = menu.findItem(R.id.filterDown)

        itemOrdHead.setOnMenuItemClickListener(){
            adapter.OrdHead()
        }
        itemOrdTail.setOnMenuItemClickListener(){
            adapter.OrdTail()
        }

    }
}

