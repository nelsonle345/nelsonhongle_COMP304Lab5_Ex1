package com.nelson.le

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var landmarkType: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.map_activity)

        // Retrieve the selected landmark type
        landmarkType = intent.getStringExtra("LANDMARK_TYPE") ?: ""

        // Initialize the map
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // Enable the back button in the toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Fetch landmarks of the selected type
        val landmarks = fetchLandmarks(landmarkType)

        // Display landmarks on the map
        displayLandmarksOnMap(landmarks)
    }

    private fun fetchLandmarks(landmarkType: String?): List<Landmark> {
        return when (landmarkType) {
            "Old Buildings" -> {
                listOf(
                    Landmark("Casa Loma", "1 Austin Terrace", 43.6780, -79.4095, "Old Building"),
                    Landmark("Distillery District", "55 Mill St", 43.6500, -79.3617, "Old Building"),
                    Landmark("St. Lawrence Hall", "157 King St E", 43.6483, -79.3720, "Old Building"),
                    Landmark("Gooderham Building", "49 Wellington St E", 43.6480, -79.3736, "Old Building"),
                    Landmark("Osgoode Hall", "130 Queen St W", 43.6511, -79.3837, "Old Building")
                )
            }
            "Museums" -> {
                listOf(
                    Landmark("Royal Ontario Museum", "100 Queens Park", 43.6684, -79.3947, "Museum"),
                    Landmark("Art Gallery of Ontario", "317 Dundas St W", 43.6544, -79.3923, "Museum"),
                    Landmark("Bata Shoe Museum", "327 Bloor St W", 43.6672, -79.4004, "Museum"),
                    Landmark("Ontario Science Centre", "770 Don Mills Rd", 43.7165, -79.3406, "Museum"),
                    Landmark("Textile Museum of Canada", "55 Centre Ave", 43.6544, -79.3860, "Museum")
                )
            }
            "Stadiums" -> {
                listOf(
                    Landmark("Rogers Centre", "1 Blue Jays Way", 43.6414, -79.3894, "Stadium"),
                    Landmark("Scotiabank Arena", "40 Bay St", 43.6434, -79.3791, "Stadium"),
                    Landmark("BMO Field", "170 Princes' Blvd", 43.6332, -79.4185, "Stadium"),
                    Landmark("Coca-Cola Coliseum", "45 Manitoba Dr", 43.6334, -79.4180, "Stadium"),
                    Landmark("Varsity Stadium", "299 Bloor St W", 43.6690, -79.3987, "Stadium")
                )
            }
            "Attractions" -> {
                listOf(
                    Landmark("CN Tower", "301 Front St W", 43.6426, -79.3871, "Attraction"),
                    Landmark("Ripley's Aquarium", "288 Bremner Blvd", 43.6424, -79.3860, "Attraction"),
                    Landmark("Toronto Islands", "9 Queens Quay W", 43.6226, -79.3785, "Attraction"),
                    Landmark("High Park", "1873 Bloor St W", 43.6465, -79.4634, "Attraction"),
                    Landmark("Toronto Zoo", "2000 Meadowvale Rd", 43.8177, -79.1805, "Attraction")
                )
            }
            else -> emptyList()
        }
    }


    private fun displayLandmarksOnMap(landmarks: List<Landmark>) {
        // TODO: Implement logic to display landmarks on the map
        // This could involve adding markers to the GoogleMap
        for (landmark in landmarks) {
            val location = LatLng(landmark.latitude, landmark.longitude)
            mMap.addMarker(MarkerOptions().position(location).title(landmark.name))
        }

        // Move camera to the first landmark (if available)
        if (landmarks.isNotEmpty()) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                LatLng(landmarks[0].latitude, landmarks[0].longitude), 12f)
            )
        }
    }

    // Handle the back button click event
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // Handle back button click
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

