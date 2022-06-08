package com.example.appciudades.ui.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.icu.util.IslamicCalendar
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.appciudades.MyBeer
import com.example.appciudades.R
import com.example.appciudades.databinding.FragmentAboutBeerBinding
import com.example.appciudades.dominio.models.Cerveza
import com.example.appciudades.viewModel.MyVM
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AboutBeerFragment : Fragment() {
    private lateinit var binding : FragmentAboutBeerBinding
    private lateinit var mMap: GoogleMap
    private lateinit var localizacion: LatLng

    private lateinit var escogida : Cerveza
    private lateinit var locationClient: FusedLocationProviderClient
    private val  granted = PackageManager.PERMISSION_GRANTED
    private val acl = Manifest.permission.ACCESS_COARSE_LOCATION
    private val ubicacionPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ){

        ifPermiso(mMap)
    }



    private val callback = OnMapReadyCallback { googleMap ->

        mMap = googleMap
        locationClient = context?.let { LocationServices.getFusedLocationProviderClient(it) }!!
        googleMap.mapType = GoogleMap.MAP_TYPE_HYBRID


        googleMap.setMinZoomPreference(0.25f)
        googleMap.setMaxZoomPreference(17.5f)

        crearMarcador(escogida,googleMap)
        googleMap.setOnMapLoadedCallback {
            localizacion()
        }



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutBeerBinding.inflate(inflater,container,false)
        return (binding.root)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapa) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
        binding.toolbar4.title = "Mapa de Cervezas"
        @SuppressLint("StaticFieldLeak")
        val nav = findNavController()
        binding.toolbar4.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        binding.toolbar4.setOnClickListener {
            nav.navigate(R.id.listaFragment)
        }
        corrutina()

    }

    private fun corrutina(){
        val serve = arguments?.getInt("cerveza")
        val myBeer = requireActivity().application as MyBeer
        val vm: MyVM by activityViewModels(){
            MyVM.MyViewModelFactory(myBeer.repositorio)
        }

        if (serve != null) {
            CoroutineScope(Dispatchers.IO).launch {
                escogida = vm.unaBeer(serve)
                val distance = Location(LocationManager.GPS_PROVIDER).apply {
                    latitude = escogida.latitud
                    longitude = escogida.longitud
                }

                binding.CervImg.setImageURI(escogida.img.toUri())
                binding.toolbar4.title = escogida.nombre
                binding.ciudad.text = escogida.ciudad
                binding.pais.text = escogida.pais
                binding.nam.text = escogida.nombre
                binding.grado.text = escogida.grados.toString()+"º"

            }
        }
    }

    private fun crearMarcador(cerveza: Cerveza,mapa:GoogleMap){
        localizacion = LatLng(cerveza.latitud,cerveza.longitud)


        val precision = LatLngBounds.builder()
            .include(localizacion)
            .build()

        val mov = MarkerOptions()
            .position(localizacion)


        val marcador = mapa.addMarker(mov)
        marcador?.tag = "marcadorlugar"

        mapa.animateCamera(CameraUpdateFactory.newLatLngBounds(precision, 10))


    }


    @SuppressLint("SetTextI18n")
    private fun localizacion(){
        if (context?.let { ActivityCompat.checkSelfPermission(it, acl) }
            != granted && context?.let { ActivityCompat.checkSelfPermission(it, acl)
            } != granted) {
            return
        }
        locationClient.lastLocation.addOnSuccessListener { location ->

            val distance = Location(LocationManager.GPS_PROVIDER).apply {
                latitude = escogida.latitud
                longitude = escogida.longitud
            }
            val distanciaTotal = location.distanceTo(distance).toString()
            binding.dstancia.text = "${distanciaTotal.toDouble().toInt()} km"
        }
    }

    private fun ifPermiso(googleMap: GoogleMap): Boolean {
        if (context?.let { permiso(it, Manifest.permission.ACCESS_FINE_LOCATION) } == granted
            && context?.let { permiso(it, Manifest.permission.ACCESS_COARSE_LOCATION) } == granted
        ) {
            if (context?.let {
                    ActivityCompat.checkSelfPermission(
                        it,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )
                } != PackageManager.PERMISSION_GRANTED && context?.let {
                    ActivityCompat.checkSelfPermission(
                        it,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                } != PackageManager.PERMISSION_GRANTED
            ) {
                return googleMap.isMyLocationEnabled
            }
            googleMap.isMyLocationEnabled = true


        }
        return googleMap.isMyLocationEnabled
    }

    private fun permiso(context: Context, permission: String): Int {
        return ActivityCompat.checkSelfPermission(context,permission)
    }

    private fun checkPermiso(googleMap: GoogleMap){
        ifPermiso(googleMap)
        if (!ifPermiso(googleMap)){
            ubicacionPermissionLauncher.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION ))
        }
    }



}