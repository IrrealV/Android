package com.example.covidmap

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.covidmap.databinding.ActivityMapsBinding
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.LatLngBounds
import java.text.SimpleDateFormat
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var locationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private val  granted = PackageManager.PERMISSION_GRANTED
    private val ubicacionPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ){

        ifPermiso(mMap)
    }
//    val contract = registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) {
//        if (it.resultCode == RESULT_OK) {
//            tomarUbicacionEnDirecto()
//        } else {
//            // No se puede tomar la ubicación con la configuración actual
//        }
//    }


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
        locationClient= LocationServices.getFusedLocationProviderClient(this)
        locationRequest = LocationRequest.create()
        locationRequest.interval = 10000
        locationRequest.fastestInterval = 5000
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mMap = googleMap
        checkPermiso(mMap)
        mMap.mapType = GoogleMap.MAP_TYPE_HYBRID

        val uiSettings = mMap.uiSettings
        uiSettings.isZoomGesturesEnabled = true

        val madrid = LatLng(40.47883646461693, -3.7692950517063832)
        val cuZoom = CameraUpdateFactory.newLatLngZoom(
            madrid,
            7.5f)
        mMap.moveCamera(cuZoom)


        mMap.setMinZoomPreference(7.5f)
        mMap.setMaxZoomPreference(17.5f)

        mMap.setOnMapLoadedCallback {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return@setOnMapLoadedCallback
            }
            locationClient.lastLocation.addOnSuccessListener { location ->
                val aqui = LatLng(location.latitude,location.longitude)
                val precision = LatLngBounds.builder()
                    .include(aqui)
                    .build()
                mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(precision, 10))
            }

        }


    }

    private fun ifPermiso(googleMap: GoogleMap): Boolean{
        if (permiso(this, Manifest.permission.ACCESS_FINE_LOCATION) == granted
            && permiso(this, Manifest.permission.ACCESS_COARSE_LOCATION) == granted
        ) {
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

//    private fun tomarUbicacionEnDirecto() {
//        if (ActivityCompat.checkSelfPermission(
//                this,
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                this,
//                Manifest.permission.ACCESS_COARSE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            return
//        }
//        locationClient.requestLocationUpdates(
//            locationRequest,
//            locationCallback,
//            Looper.getMainLooper()
//        )
//    }
//
//    private val locationCallback = object : LocationCallback() {
//        override fun onLocationResult(p0: LocationResult) {
//            val lastLocation = p0.lastLocation
//            imprimirUbicacion(lastLocation)
//        }
//    }
//
//    private fun imprimirUbicacion(location: Location) {
//
//        val latitud = location.latitude
//        val longitud = location.longitude
//
//    }


}