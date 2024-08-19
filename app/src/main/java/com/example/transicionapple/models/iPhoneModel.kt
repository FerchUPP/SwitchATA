package com.example.transicionapple.models

data class iPhoneModel(
    val modelVersion: String = "",
    val fingerprintSensor: Boolean = false,
    val proximitySensor: Boolean = false,
    val gyroscopeSensor: Boolean = false,
    val compassSensor: Boolean = false,
    val screenSize: String = "",
    val resolution: String = "",
    val aspectRatio: String = "",
    val battery: Int = 0,
    val processor: String = "",
    val ram: String = "",
    val storage: String = "",
    val mainCamera: Int = 0,
    val frontCamera: Int = 0,
    val priceMin: Double = 0.0,
    val priceMax: Double = 0.0,
    val imageName: String = "",
    val zoomMax: Double = 0.0,
    val noMdl: Int = 0,
)
