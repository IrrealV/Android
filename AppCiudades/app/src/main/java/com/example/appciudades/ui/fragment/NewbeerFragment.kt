package com.example.appciudades.ui.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.estech.mybeers.utils.MyMapView
import com.example.appciudades.MyBeer
import com.example.appciudades.R
import com.example.appciudades.databinding.FragmentNewbeerBinding
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


class NewbeerFragment : Fragment() {

    private lateinit var binding: FragmentNewbeerBinding
    private lateinit var mMap: GoogleMap
    private lateinit var localizacion : LatLng
    private lateinit var locationClient: FusedLocationProviderClient
    private var contador = 0
    private val  granted = PackageManager.PERMISSION_GRANTED
    private val acl = Manifest.permission.ACCESS_COARSE_LOCATION
    private val ubicacionPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ){
        ifPermiso(mMap)
    }

    @SuppressLint("SetTextI18n")
    private val callback = OnMapReadyCallback { googleMap ->
        mMap = googleMap
        localizacion = LatLng(0.0, 0.0)
        googleMap.mapType = GoogleMap.MAP_TYPE_HYBRID
        locationClient = context?.let { LocationServices.getFusedLocationProviderClient(it) }!!

        checkPermiso(googleMap)

        googleMap.setMinZoomPreference(0.5f)
        googleMap.setMaxZoomPreference(17.5f)

        binding.miubi.setOnClickListener{
            googleMap.clear()
            localizacion(googleMap)
        }

        googleMap.setOnMapClickListener {
            googleMap.clear()
            googleMap.addMarker(MarkerOptions().position(it))
            localizacion = it
            val lat= localizacion.latitude.toInt()
            val long = localizacion.longitude.toInt()
            binding.posicion.text = "lat/lng:(${lat},${long}) simplificado"


        }
        googleMap.setOnCameraMoveStartedListener { i ->
            if (i == GoogleMap.OnCameraMoveStartedListener.REASON_GESTURE) {
                blockScrollView()
            }
        }
        googleMap.setOnCameraMoveCanceledListener { enableScrollView() }


    }

    private fun enableScrollView() {
        binding.scroll.requestDisallowInterceptTouchEvent(false)
    }

    private fun blockScrollView() {
        binding.scroll.requestDisallowInterceptTouchEvent(true)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewbeerBinding.inflate(inflater,container,false)
        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
        @SuppressLint("StaticFieldLeak")
        val nav = findNavController()
        val myBeer = requireActivity().application as MyBeer
        val vm: MyVM by activityViewModels(){
            MyVM.MyViewModelFactory(myBeer.repositorio)
        }
        val toolbar = binding.toolbar3
        toolbar.title = "Mapa de Cervezas"
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        toolbar.setOnClickListener {
            nav.navigate(R.id.listaFragment)
        }

        binding.sumar.setOnClickListener {
            var num = binding.grad.text.toString().toDouble()
            num += 0.10
            binding.grad.setText(num.toString())
        }

        binding.restar.setOnClickListener {
            var num = binding.grad.text.toString().toDouble()
            num -= 0.10
            binding.grad.setText(num.toString())
        }



        binding.submit.setOnClickListener{
            comprobCeldas()
            if(contador == 0){
                vm.insertarCerveza(
                    Cerveza(
                        binding.brand.text.toString(),
                        binding.city.text.toString(),
                        binding.country.text.toString(),
                        binding.grad.text.toString().toDouble(),
                        localizacion.latitude,
                        localizacion.longitude,
                        binding.probado.isChecked,
                        localizacion.toString()
                    )
                )
                nav.navigate(R.id.listaFragment)
            }
        }



    }


    @SuppressLint("SetTextI18n")
    private fun localizacion(mapa:GoogleMap){
        if (context?.let { ActivityCompat.checkSelfPermission(it, acl) }
            != granted && context?.let { ActivityCompat.checkSelfPermission(it, acl)
            } != granted) {
            return
        }
        locationClient.lastLocation.addOnSuccessListener { location ->
            val aqui = LatLng(location.latitude, location.longitude)
            localizacion = aqui

            val precision = LatLngBounds.builder()
                .include(aqui)
                .build()

            val mov = MarkerOptions()
                .position(aqui)
                .title(binding.brand.toString())
                .snippet("pulsa para ver mas detalles")

            val marcador = mapa.addMarker(mov)
            marcador?.tag = "marcadorlugar"

            mapa.animateCamera(CameraUpdateFactory.newLatLngBounds(precision, 10))

            binding.posicion.text = "lat/lng:(${location.latitude.toInt()},${location.longitude.toInt()}) simplificado"


        }
    }

    private fun checkVacio(text: EditText,celda:String) :Boolean{
        if(celda.isEmpty()){
            text.error = "La celda está vacía"
            return false
        }
        return true
    }

    private fun comprobCeldas(){
        contador = 0

        val marca = binding.brand.text.toString()
        val ciudadfab = binding.city.text.toString()
        val pais = binding.country.text.toString()
        val grados = binding.grad.text.toString()
        val bMarca = checkVacio(binding.brand,marca)
        val bciudad =checkVacio(binding.city,ciudadfab)
        val bpais =checkVacio(binding.country,pais)
        val bgrados =checkVacio(binding.grad,grados)

        val comprobarTrue = arrayOf(bMarca,bciudad,bpais,bgrados,checkMapa())
        comprobarTrue.forEach {
            if(!it){
                contador += 1
            }
        }
    }

    private fun checkMapa() : Boolean{
        if(localizacion.latitude == 0.0){
            Toast.makeText(context, "Oye que no has metido donde es", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
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