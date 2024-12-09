package com.example.try_joural_2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class AddPlantActivity : AppCompatActivity() {

    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_plant)

        val plantNameInput = findViewById<EditText>(R.id.plantNameInput)
        val plantDescriptionInput = findViewById<EditText>(R.id.plantDescriptionInput)
        val savePlantButton = findViewById<Button>(R.id.savePlantButton)

        savePlantButton.setOnClickListener {
            val name = plantNameInput.text.toString()
            val description = plantDescriptionInput.text.toString()

            val plant = hashMapOf(
                "name" to name,
                "description" to description
            )

            firestore.collection("plants").add(plant).addOnSuccessListener {
                finish() // Close the activity after saving
            }
        }
    }
}
