package com.example.try_joural_2

import Plant
import PlantAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var plantRecyclerView: RecyclerView
    private val firestore = FirebaseFirestore.getInstance()
    private val plantList = mutableListOf<Plant>()
    private lateinit var adapter: PlantAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        plantRecyclerView = findViewById(R.id.plantRecyclerView)
        plantRecyclerView.layoutManager = LinearLayoutManager(this)

        adapter = PlantAdapter(plantList)
        plantRecyclerView.adapter = adapter

        fetchPlants()

        findViewById<Button>(R.id.addPlantButton).setOnClickListener {
            startActivity(Intent(this, AddPlantActivity::class.java))
        }
    }

    private fun fetchPlants() {
        firestore.collection("plants").get().addOnSuccessListener { result ->
            plantList.clear()
            for (document in result) {
                val plant = Plant(
                    name = document.getString("name") ?: "",
                    description = document.getString("description") ?: ""
                )
                plantList.add(plant)
            }
            adapter.notifyDataSetChanged()
        }
    }
}
