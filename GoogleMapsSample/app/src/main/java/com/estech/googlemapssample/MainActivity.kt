package com.estech.googlemapssample

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.estech.googlemapssample.databinding.ActivityMainBinding
import com.estech.googlemapssample.databinding.CustomWindowInfoBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        fragment.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {

        /* TIPO DE MAPA */
        map.mapType = GoogleMap.MAP_TYPE_HYBRID
        GoogleMap.MAP_TYPE_NONE
        GoogleMap.MAP_TYPE_NORMAL
        GoogleMap.MAP_TYPE_SATELLITE
        GoogleMap.MAP_TYPE_TERRAIN

        map.isTrafficEnabled = true  //trafico activado
        val traffic = map.isTrafficEnabled  //devuelve true si el tráfico está activado


        val uiSettings = map.uiSettings
        uiSettings.isZoomControlsEnabled = true //controles de zoom

        uiSettings.isCompassEnabled = true //mostrar la brújula

        uiSettings.isZoomGesturesEnabled = true //gestos de zoom

        uiSettings.isScrollGesturesEnabled = true //Gestos de scroll

        uiSettings.isTiltGesturesEnabled = true //Gestos de ángulo

        uiSettings.isRotateGesturesEnabled = true //Gestos de rotación


//         asignar max y min zoom al mapa (3 a 22)
        map.setMinZoomPreference(6.0f)
        map.setMaxZoomPreference(14.0f)

//        Mover cámara
        val latLng = LatLng(38.38, -3.78)  //LatLng es latitud y longitud en double
        val cu = CameraUpdateFactory.newLatLng(latLng)
        val cuZoom = CameraUpdateFactory.newLatLngZoom(
            latLng,
            8f
        ) //float determina el nivel de zoom, siendo 1 el más lejano

//         mueve la cámara a una ubicación
        map.moveCamera(cuZoom)

        binding.fabRefresh.setOnClickListener {
            val madrid = CameraUpdateFactory.newLatLngZoom(
                LatLng(40.42062134847416, -3.7042661462520288),
                7f
            )
            map.animateCamera(madrid)
        }

        binding.fabZoom.setOnClickListener {
            val maxZoom = CameraUpdateFactory.zoomTo(20f)
            map.animateCamera(maxZoom)
        }

//         centrar mapa en un área
        // centrar mapa en un área
        val madrid = LatLng(40.47883646461693, -3.7692950517063832)
        val paris = LatLng(48.92116447022164, 2.4269937759977664)
        val roma = LatLng(41.835314517079205, 12.446524646327886)
        val region = LatLngBounds.Builder()
            .include(madrid)
            .include(paris)
            .include(roma)
            .build()

// map.moveCamera(CameraUpdateFactory.newLatLngZoom(australia.center, 3f))

// listener para esperar que el mapa esté cargado
        map.setOnMapLoadedCallback {
            map.animateCamera(CameraUpdateFactory.newLatLngBounds(region, 20))
        }



//         listener al pulsar un punto de interés
//
//         listener al pulsar un punto de interés
        map.setOnPoiClickListener { pointOfInterest ->
            Toast.makeText(
                this@MainActivity,
                "Nombre del sitio: " + pointOfInterest.name,
                Toast.LENGTH_SHORT
            ).show()
            val ubicacion = pointOfInterest.latLng
            map.animateCamera(CameraUpdateFactory.newLatLng(ubicacion))
        }

//         añadir marker
        val options1 = MarkerOptions()
            .position(LatLng(38.5, -3.3))
            .title("Sitio")
            .snippet("Pulsa aquí para acceder")
            .alpha(.6f)
            .rotation(90f)
        val marker1 = map.addMarker(options1)

        val options2 = MarkerOptions()
            .position(LatLng(38.09418604624217, -3.6312538))
            .title("Sitio2")
            .snippet("Pulsa aquí para acceder")
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker))
        val marker2 = map.addMarker(options2)

        val options3 = MarkerOptions()
            .position(LatLng(39.5, -2.3))
            .title("Sitio3")
            .snippet("Pulsa aquí para acceder")
            .flat(true)
        val marker3 = map.addMarker(options3)

        val options4 = MarkerOptions()
            .position(LatLng(37.5, -4.3))
            .title("Sitio4")
            .snippet("Pulsa aquí para acceder")
            .flat(true)
            .draggable(true)
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA))
        val marker4 = map.addMarker(options4)
        marker4?.tag = "mimarker"


//         listener draggable para arrastrar y solar marker
        map.setOnMarkerDragListener(object : GoogleMap.OnMarkerDragListener {
            override fun onMarkerDrag(p0: Marker) {
            }

            override fun onMarkerDragEnd(marker: Marker) {
                if (marker.tag == "mimarker") {
                    val latLng1 = marker.position
                    val latitud = latLng1.latitude
                    val longitud = latLng1.longitude
                    Toast.makeText(
                        this@MainActivity,
                        "Latitud: $latitud, Longitud: $longitud",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onMarkerDragStart(p0: Marker) {
            }
        })

//        click en el marker
        map.setOnMarkerClickListener { marker ->
            if (marker == marker2) {
                Toast.makeText(this@MainActivity, "Escuela Estech", Toast.LENGTH_SHORT).show()
            }
            false
        }

//        click en cartel del marker
        map.setOnInfoWindowClickListener { marker ->
            if (marker == marker2) {
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse("https://escuelaestech.es")
                startActivity(i)
            }
        }

//         eliminar marker

//        Custom Window
        map.setInfoWindowAdapter(object : GoogleMap.InfoWindowAdapter {
            override fun getInfoContents(p0: Marker): View {
                val binding = CustomWindowInfoBinding.inflate(layoutInflater)
                return binding.root
            }

            override fun getInfoWindow(p0: Marker): View? {
                return null
            }

        })

//        Polyline
        var rectOptions = PolylineOptions()
            .color(Color.RED)
            .width(10f)
            .clickable(true)
            .add(LatLng(38.096821, -3.627834)) //1er punto
            .add(LatLng(38.096855, -3.627126)) //este
            .add(LatLng(38.097779, -3.627174)) //norte
            .add(LatLng(38.097760, -3.627908)) //oeste
            .add(LatLng(38.097496, -3.628524)) //suroeste

        val campoFutbol = map.addPolyline(rectOptions)

        rectOptions = PolylineOptions()
            .color(Color.YELLOW)
            .width(10f)
            .clickable(true)
            .add(LatLng(38.096821, -3.627834)) //init point
            .add(LatLng(38.09745732808713, -3.6278839818218995))

        map.addPolyline(rectOptions)


//        Polygon
        val options = PolygonOptions().strokeColor(Color.RED)
            .strokeWidth(20f)
            .fillColor(ContextCompat.getColor(this, R.color.semiTransparent))
            .zIndex(10f)
            .clickable(true)
            .add(LatLng(43.75341412588365, -9.593843256556998)) //1er punto
            .add(LatLng(42.986742508776935, 3.5018585563121136)) // Hacia al este
            .add(LatLng(36.401581867949574, 0.8211947624026644)) // Hacia el norte
            .add(LatLng(36.860036902084985, -10.560640034688271)) // Hacia el oeste
            .add(LatLng(43.75341412588365, -9.593843256556998)) // Hacia el suroeste

        val spainLine: Polygon = map.addPolygon(options)


//         circle
        val circleOptions = CircleOptions()
            .center(LatLng(38.93515236530402, -100.83512605947585))
            .radius(2000000.0)
            .strokeColor(Color.BLUE)
            .strokeWidth(10f)
            .clickable(true)
            .fillColor(ContextCompat.getColor(this, R.color.semiTransparent))
        val circleUsa: Circle = map.addCircle(circleOptions)

//         click sobre las formas
        map.setOnPolylineClickListener { polyline ->
            if (polyline == campoFutbol) {
                Toast.makeText(this@MainActivity, "Dar vueltas al campo", Toast.LENGTH_SHORT).show()
            }
        }

        map.setOnPolygonClickListener { polygon ->
            if (polygon == spainLine)
                Toast.makeText(this@MainActivity, "España incomunicada", Toast.LENGTH_SHORT).show()

        }

        map.setOnCircleClickListener { circle ->
            if (circle == circleUsa) {
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setMessage("Disparar misil?")
                builder.setPositiveButton(
                    "SI"
                ) { dialog, which ->
                    circleUsa.remove()
                    val mCircleOptions = CircleOptions()
                        .center(LatLng(38.93515236530402, -100.83512605947585))
                        .radius(2000000.0)
                        .strokeColor(Color.BLUE)
                        .fillColor(Color.BLUE)
                        .strokeWidth(10f)
                    map.addCircle(mCircleOptions)
                }
                builder.setNegativeButton(
                    "NO"
                ) { dialog, which ->
                    circleUsa.remove()
                }
                builder.create().show()
            }
        }
    }
}