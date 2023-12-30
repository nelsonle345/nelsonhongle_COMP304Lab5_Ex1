package com.nelson.le

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), LandmarkAdapter.OnItemClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: LandmarkAdapter

    private val landmarkMap: MutableMap<String, List<Landmark>> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize landmarkMap with landmarks
        initializeLandmarks()

        // Get all landmark types
        val landmarkTypes = landmarkMap.keys.toList()

        // Initialize the adapter with the list of landmark types
        adapter = LandmarkAdapter(ArrayList(landmarkTypes), this)
        recyclerView.adapter = adapter
    }

    private fun initializeLandmarks() {
        val oldBuildings = listOf(
            Landmark("Casa Loma", "1 Austin Terrace", 43.6780, -79.4095, "Old Building"),
            Landmark("Distillery District", "55 Mill St", 43.6500, -79.3617, "Old Building"),
            Landmark("St. Lawrence Hall", "157 King St E", 43.6483, -79.3720, "Old Building"),
            Landmark("Gooderham Building", "49 Wellington St E", 43.6480, -79.3736, "Old Building"),
            Landmark("Osgoode Hall", "130 Queen St W", 43.6511, -79.3837, "Old Building"),
        )

        val museums = listOf(
            Landmark("Royal Ontario Museum", "100 Queens Park", 43.6684, -79.3947, "Museum"),
            Landmark("Art Gallery of Ontario", "317 Dundas St W", 43.6544, -79.3923, "Museum"),
            Landmark("Bata Shoe Museum", "327 Bloor St W", 43.6672, -79.4004, "Museum"),
            Landmark("Ontario Science Centre", "770 Don Mills Rd", 43.7165, -79.3406, "Museum"),
            Landmark("Textile Museum of Canada", "55 Centre Ave", 43.6544, -79.3860, "Museum"),
        )

        val stadiums = listOf(
            Landmark("Rogers Centre", "1 Blue Jays Way", 43.6414, -79.3894, "Stadium"),
            Landmark("Scotiabank Arena", "40 Bay St", 43.6434, -79.3791, "Stadium"),
            Landmark("BMO Field", "170 Princes' Blvd", 43.6332, -79.4185, "Stadium"),
            Landmark("Coca-Cola Coliseum", "45 Manitoba Dr", 43.6334, -79.4180, "Stadium"),
            Landmark("Varsity Stadium", "299 Bloor St W", 43.6690, -79.3987, "Stadium"),
        )

        val attractions = listOf(
            Landmark("CN Tower", "301 Front St W", 43.6426, -79.3871, "Attraction"),
            Landmark("Ripley's Aquarium", "288 Bremner Blvd", 43.6424, -79.3860, "Attraction"),
            Landmark("Toronto Islands", "9 Queens Quay W", 43.6226, -79.3785, "Attraction"),
            Landmark("High Park", "1873 Bloor St W", 43.6465, -79.4634, "Attraction"),
            Landmark("Toronto Zoo", "2000 Meadowvale Rd", 43.8177, -79.1805, "Attraction"),
        )

        landmarkMap["Old Buildings"] = oldBuildings
        landmarkMap["Museums"] = museums
        landmarkMap["Stadiums"] = stadiums
        landmarkMap["Attractions"] = attractions
    }

    override fun onItemClick(position: Int) {
        // Get the selected landmark type
        val selectedLandmarkType = adapter.getLandmarkType(position)

        // Launch MapActivity with selected landmark type
        val intent = Intent(this, MapActivity::class.java).apply {
            putExtra("LANDMARK_TYPE", selectedLandmarkType)
        }
        startActivity(intent)
    }

}
