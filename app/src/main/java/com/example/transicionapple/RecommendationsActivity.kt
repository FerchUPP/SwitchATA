package com.example.transicionapple

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import com.example.transicionapple.firestore.FirestoreHelper
import com.google.firebase.firestore.FirebaseFirestore

class RecommendationsActivity : ComponentActivity() {

    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recommendations)

        db = FirebaseFirestore.getInstance()

        val recommendButton: Button = findViewById(R.id.recommendButton)
        val budgetInput: EditText = findViewById(R.id.budgetInput)

        val checkBoxBattery: CheckBox = findViewById(R.id.checkBoxBattery)
        val checkBoxCameras: CheckBox = findViewById(R.id.checkBoxCameras)
        val checkBoxScreenSize: CheckBox = findViewById(R.id.checkBoxScreenSize)

        val backToMenuButton: ImageButton = findViewById(R.id.backToMenuButton)
        backToMenuButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }

        recommendButton.setOnClickListener {
            val budget = budgetInput.text.toString().toDoubleOrNull()

            if (budget != null) {
                FirestoreHelper.getAlliPhoneModels(db,
                    { models -> // Eliminar el onSuccess explícito y utilizar directamente la lambda
                        var recommendedModels = models.filter { budget >= it.priceMin }

                        // Escenario 1: Si no hay modelos dentro del presupuesto mínimo
                        if (recommendedModels.isEmpty()) {
                            showAlert("No existe un modelo disponible con ese presupuesto.")
                            return@getAlliPhoneModels
                        }

                        when {
                            checkBoxBattery.isChecked -> {
                                recommendedModels = listOf(recommendedModels.maxByOrNull { it.battery }!!)
                            }
                            checkBoxCameras.isChecked -> {
                                // Filtrar modelos que estén dentro del presupuesto y tengan Pro Max en el nombre
                                val maxZoomModels = recommendedModels
                                    .filter { it.zoomMax > 0 } // Filtrar modelos con zoomMax positivo
                                    .groupBy { it.zoomMax } // Agrupar por zoomMax
                                    .maxByOrNull { it.key }?.value // Seleccionar los que tienen el mayor zoomMax

                                // De los modelos con el mejor zoomMax, seleccionar el que tiene el noMdl más alto
                                recommendedModels = maxZoomModels
                                    ?.maxByOrNull { it.noMdl }
                                    ?.let { listOf(it) }
                                    ?: emptyList()
                            }

                            checkBoxScreenSize.isChecked -> {
                                recommendedModels = recommendedModels
                                    .filter { it.modelVersion.contains("Pro Max") }
                                    .maxByOrNull { it.noMdl }
                                    ?.let { listOf(it) }
                                    ?: emptyList()

                                // Escenario 2: Si no hay modelos Pro Max dentro del presupuesto
                                if (recommendedModels.isEmpty()) {
                                    showAlert("No existe un modelo disponible con ese presupuesto y esa característica.")
                                    return@getAlliPhoneModels
                                }
                            }
                        }

                        if (recommendedModels.isNotEmpty()) {
                            val recommendedModel = recommendedModels.first()
                            val intent = Intent(this, RecommendationResultActivity::class.java).apply {
                                putExtra("modelVersion", recommendedModel.modelVersion)
                                putExtra("screenSize", recommendedModel.screenSize)
                                putExtra("resolution", recommendedModel.resolution)
                                putExtra("battery", recommendedModel.battery.toString()) // Pasar como String
                                putExtra("mainCamera", recommendedModel.mainCamera.toString())
                                putExtra("zoomMax", recommendedModel.zoomMax.toString())
                                putExtra("frontCamera", recommendedModel.frontCamera.toString())
                                putExtra("storage", recommendedModel.storage)
                                putExtra("modelImageName", recommendedModel.imageName)
                            }
                            startActivity(intent)
                        } else {
                            Log.d("RecommendationsActivity", "No models found for the given budget.")
                        }
                    },
                    { e -> // Manejar el onFailure aquí
                        Log.w("RecommendationsActivity", "Error getting documents", e)
                    }
                )
            }
        }
    }

    // Método para mostrar una alerta
    private fun showAlert(message: String) {
        AlertDialog.Builder(this)
            .setTitle("Atención")
            .setMessage(message)
            .setPositiveButton("Aceptar", null)
            .create()
            .show()
    }
}