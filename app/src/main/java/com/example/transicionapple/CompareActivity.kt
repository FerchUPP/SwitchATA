package com.example.transicionapple

import CompareAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.Spinner
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.transicionapple.firestore.FirestoreHelper
import com.example.transicionapple.models.iPhoneModel
import com.google.firebase.firestore.FirebaseFirestore

class CompareActivity : ComponentActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var models: List<iPhoneModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compare)

        // Inicializar Firestore
        db = FirebaseFirestore.getInstance()

        // Configurar RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Configurar Spinners para seleccionar modelos
        val spinnerModel1: Spinner = findViewById(R.id.spinnerModel1)
        val spinnerModel2: Spinner = findViewById(R.id.spinnerModel2)

        // Configurar botón de comparar
        val compareButton: Button = findViewById(R.id.compareButton)

        // Configurar botón para regresar al menú
        val backToMenuButton: ImageButton = findViewById(R.id.backToMenuButton)
        backToMenuButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }

        // Obtener modelos de Firestore y poblar los Spinners
        FirestoreHelper.getAlliPhoneModels(db,
            onSuccess = { models ->
                this.models = models
                val modelNames = models.map { it.modelVersion }
                val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, modelNames)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                spinnerModel1.adapter = adapter
                spinnerModel2.adapter = adapter
            },
            onFailure = { e ->
                Log.w("CompareActivity", "Error getting documents", e)
            }
        )

        // Configurar el botón para comparar modelos
        compareButton.setOnClickListener {
            val model1 = spinnerModel1.selectedItem.toString()
            val model2 = spinnerModel2.selectedItem.toString()

            val selectedModels = models.filter { it.modelVersion == model1 || it.modelVersion == model2 }

            if (selectedModels.size == 2) {
                val adapter = CompareAdapter(selectedModels)
                recyclerView.adapter = adapter
            } else {
                Log.e("CompareActivity", "Error: One or both selected models not found")
            }
        }
    }
}