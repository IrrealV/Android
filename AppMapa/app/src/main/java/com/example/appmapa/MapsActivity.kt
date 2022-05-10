package com.example.appmapa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.appmapa.databinding.ActivityMapsBinding
import com.example.appmapa.databinding.VistaLugarBinding
import com.example.appmapa.dataclass.Lugar
import com.example.appmapa.retrofit.Repositorio
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var lugar: Lugar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN

        //Variables
        val repositorio = Repositorio()
        val linares = LatLng(38.09691852863063, -3.636979605769824)
        val uiSets = mMap.uiSettings
        val latLng = linares//LatLng es latitud y longitud en double
        val cuZoom = CameraUpdateFactory.newLatLngZoom(latLng,8f)
        val region = LatLngBounds.Builder()
            .include(linares)
            .build()

        // Añade un marcador en Linares y mueve la cámara
        mMap.addMarker(MarkerOptions().position(linares).title("Marcador Linares"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(linares))


        //Controles
        uiSets.isZoomControlsEnabled = true //controles de zoom

        uiSets.isCompassEnabled = true //mostrar la brújula

        uiSets.isZoomGesturesEnabled = true //gestos de zoom

        uiSets.isScrollGesturesEnabled = true //Gestos de scroll

        uiSets.isTiltGesturesEnabled = true //Gestos de ángulo

        uiSets.isRotateGesturesEnabled = true //Gestos de rotación

        //hace zoom a la ubicación establecida

        mMap.setOnMapLoadedCallback {
            val maxZoom = CameraUpdateFactory.zoomTo(15f)
            mMap.moveCamera(cuZoom)
            mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(region, 20))
            mMap.animateCamera(maxZoom)
        }


        CoroutineScope(Dispatchers.IO).launch {
            val respuesta = repositorio.todosLosLugares()

            withContext(Dispatchers.Main){
                if(respuesta.isSuccessful) {
                    val miRespuesta = respuesta.body()
                    val lista_lugar = miRespuesta
                    lista_lugar?.let {

                        Toast.makeText(this@MapsActivity, "Exito", Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    Toast.makeText(this@MapsActivity, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        }



        //        Custom Window
        mMap.setInfoWindowAdapter(object : GoogleMap.InfoWindowAdapter {
            override fun getInfoContents(p0: Marker): View {
                val binding = VistaLugarBinding.inflate(layoutInflater)
                return binding.root
            }

            override fun getInfoWindow(p0: Marker): View? {
                return null
            }

        })


    }
}