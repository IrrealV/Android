package com.example.appciudades.ui.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.net.toUri
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.appciudades.MyBeer
import com.example.appciudades.R
import com.example.appciudades.databinding.FragmentMapsBinding
import com.example.appciudades.databinding.VistaLugarBinding
import com.example.appciudades.viewModel.MyVM
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*


class MapsFragment : Fragment() {


    private lateinit var mMap: GoogleMap
    private lateinit var binding: FragmentMapsBinding
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
        mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
        locationClient = context?.let { LocationServices.getFusedLocationProviderClient(it) }!!

        checkPermiso(googleMap)


        googleMap.setMinZoomPreference(0.5f)
        googleMap.setMaxZoomPreference(17.5f)

        googleMap.setOnMapLoadedCallback {
            cadalugar(googleMap)
            localizacion()
        }




    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapsBinding.inflate(inflater,container,false)
        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapa) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
        @SuppressLint("StaticFieldLeak")
        val nav = findNavController()
        binding.toolbar2.title = "Mapa de Cervezas"

        binding.toolbar2.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        binding.toolbar2.setOnClickListener {
            nav.navigate(R.id.listaFragment)
        }

    }



    private fun cadalugar(mapa:GoogleMap){
        val myBeer = requireActivity().application as MyBeer
        val vm: MyVM by activityViewModels(){
            MyVM.MyViewModelFactory(myBeer.repositorio)
        }

        vm.todoCerveza.value?.forEach{ place->

            val lugares = LatLng(place.latitud,place.longitud)

            mapa.addMarker(MarkerOptions().position(lugares).title(place.nombre).snippet("Pulsa para más info")
                .icon(BitmapDescriptorFactory.defaultMarker()))!!.tag = place.img


            mapa.setInfoWindowAdapter(object : GoogleMap.InfoWindowAdapter {
                override fun getInfoContents(p0: Marker): View {
                    val binding = VistaLugarBinding.inflate(layoutInflater)
                    val uri = place.img?.toUri()


                    binding.sitioImg.setImageURI(uri)
                    binding.nameTxt.text = place.nombre
                    binding.pai.text = place.pais

                    return binding.root
                }

                override fun getInfoWindow(p0: Marker): View? {
                    return null
                }

            })
            mapa.setOnInfoWindowClickListener {
                val nav = findNavController()
                val cerv = place.id
                val bundle = bundleOf("cerveza" to cerv)
                nav.navigate(R.id.action_mapsFragment_to_aboutBeerFragment,bundle)
            }




        }

    }

    private fun localizacion(){
        if (context?.let { ActivityCompat.checkSelfPermission(it, acl) }
            != granted && context?.let { ActivityCompat.checkSelfPermission(it, acl)
            } != granted) {
            return
        }
        locationClient.lastLocation.addOnSuccessListener { location ->
            val aqui = LatLng(location.latitude, location.longitude)
            val precision = LatLngBounds.builder()
                .include(aqui)
                .build()
            mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(precision, 10))

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