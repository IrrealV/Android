package com.example.appmapa

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.example.appmapa.databinding.ActivityMapsBinding
import com.example.appmapa.databinding.VistaLugarBinding
import com.example.appmapa.dataclass.Lugar
import com.example.appmapa.retrofit.Repositorio
import com.google.android.gms.maps.model.*
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.net.URL

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding


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


        val uiSets = mMap.uiSettings




        //Controles
        uiSets.isZoomControlsEnabled = true //controles de zoom

        uiSets.isCompassEnabled = true //mostrar la brújula

        uiSets.isZoomGesturesEnabled = true //gestos de zoom

        uiSets.isScrollGesturesEnabled = true //Gestos de scroll

        uiSets.isTiltGesturesEnabled = true //Gestos de ángulo

        uiSets.isRotateGesturesEnabled = true //Gestos de rotación

        //hace zoom a la ubicación establecida


        corrutina()


        // Custom Window





    }

    private fun creacion(listaLugar: ArrayList<Lugar>){
            listaLugar.forEach{ place ->
                val linares = LatLng(38.09691852863063, -3.636979605769824)
                val posicion = LatLng(place.lat!!.toDouble(),place.lng!!.toDouble())
                mMap.addMarker(MarkerOptions().position(posicion).title(place.name).snippet("Pulsa")
                    .icon(BitmapDescriptorFactory.defaultMarker(color(place.type!!.toInt()))))!!.tag = place.image

                mMap.setInfoWindowAdapter(object : GoogleMap.InfoWindowAdapter {
                    override fun getInfoContents(p0: Marker): View {
                        val tag = p0.tag as String
                        val lugar = listaLugar.filter { it.image == tag }[0]
                        val binding = VistaLugarBinding.inflate(layoutInflater)

                        val uri = "https://qastusoft.com.es/test/estech/android/images/${lugar.image}.jpg"
                            Picasso.get().load(uri).into(binding.sitioImg, MarkerCallBack(p0,uri,binding.sitioImg))

                        binding.nameTxt.text = lugar.name
                        binding.dirCalle.text = lugar.direccion
                        return binding.root
                    }

                    override fun getInfoWindow(p0: Marker): View? {
                        return null
                    }

                })
                val region = LatLngBounds.Builder()
                    .include(linares)
                    .include(posicion)
                    .build()

                // Añade un marcador en Linares y mueve la cámara
                mMap.addMarker(MarkerOptions().position(linares).title("Marcador Linares"))
                mMap.moveCamera(CameraUpdateFactory.newLatLng(linares))
                val latLng = linares//LatLng es latitud y longitud en double
                val cuZoom = CameraUpdateFactory.newLatLngZoom(latLng,8f)

                mMap.setOnMapLoadedCallback {
                    val maxZoom = CameraUpdateFactory.zoomTo(11f)
                    mMap.moveCamera(cuZoom)
                    mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(region, 20))
                    mMap.animateCamera(maxZoom)
                }

            }

    }

    inner class MarkerCallBack(val marker: Marker, val url: String,val photo: ImageView):Callback{
        override fun onSuccess(){
            if(marker.isInfoWindowShown){
                marker.hideInfoWindow()

                Picasso.get().load(url).into(photo)
                marker.showInfoWindow()
            }
        }


        override fun onError(e: Exception?){
            Log.e("Error Mapa","Error cargando imagen")
        }

    }

    private fun color(type : Int): Float {
        return when(type){
            1 -> BitmapDescriptorFactory.HUE_BLUE
            2 -> BitmapDescriptorFactory.HUE_GREEN
            3 -> BitmapDescriptorFactory.HUE_CYAN
            4 -> BitmapDescriptorFactory.HUE_YELLOW
            else ->  BitmapDescriptorFactory.HUE_RED

        }
    }


    private fun corrutina(){
        val repositorio = Repositorio()
        CoroutineScope(Dispatchers.IO).launch {
            val respuesta = repositorio.todosLosLugares()

            withContext(Dispatchers.Main){
                if(respuesta.isSuccessful) {
                    val miRespuesta = respuesta.body()
                    miRespuesta?.let {
                        creacion(miRespuesta)
                        Toast.makeText(this@MapsActivity, "Exito", Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    Toast.makeText(this@MapsActivity, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}

