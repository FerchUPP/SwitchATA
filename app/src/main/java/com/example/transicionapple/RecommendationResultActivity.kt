package com.example.transicionapple

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity

class RecommendationResultActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recommendation_result)

        val modelVersion = intent.getStringExtra("modelVersion")
        val screenSize = intent.getStringExtra("screenSize")
        val resolution = intent.getStringExtra("resolution")
        val battery = intent.getStringExtra("battery")
        val mainCamera = intent.getStringExtra("mainCamera")
        val zoomMax = intent.getStringExtra("zoomMax")
        val frontCamera = intent.getStringExtra("frontCamera")
        val storage = intent.getStringExtra("storage")
        val modelImageName = intent.getStringExtra("modelImageName")

        val modelImageView: ImageView = findViewById(R.id.modelImageView)
        val modelVersionTextView: TextView = findViewById(R.id.modelVersionTextView)
        val screenSizeTextView: TextView = findViewById(R.id.modelScreensizeTextView)
        val resolutionTextView: TextView = findViewById(R.id.modelResolution)
        val batteryTextView: TextView = findViewById(R.id.modelBatteryTextView)
        val mainCameraTextView: TextView = findViewById(R.id.modelMainCameraTextView)
        val zoomMaxTextView: TextView = findViewById(R.id.modelZoomMax)
        val frontCameraTextView: TextView = findViewById(R.id.modelFrontCameraTextView)
        val storageTextView: TextView = findViewById(R.id.modelStorageTextView)
        val backToRecommendationsButton: ImageButton = findViewById(R.id.backToRecommendationsButton)

        modelVersionTextView.text = modelVersion
        screenSizeTextView.text = "Tamaño de pantalla: $screenSize"
        resolutionTextView.text = "Resolución: $resolution"
        batteryTextView.text = "Batería: $battery mAh"
        mainCameraTextView.text = "Cámara principal: $mainCamera Mpx"
        zoomMaxTextView.text = "Zoom: Hasta ${zoomMax}x"
        frontCameraTextView.text = "Cámara frontal: $frontCamera Mpx"
        storageTextView.text = "$storage"

        // Cargar la imagen del modelo recomendado
        val resourceId = resources.getIdentifier(modelImageName, "drawable", packageName)
        modelImageView.setImageResource(resourceId)

        // Configurar el botón de regreso
        backToRecommendationsButton.setOnClickListener {
            finish() // Regresa a la actividad anterior
        }
    }
}