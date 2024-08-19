package com.example.transicionapple

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : ComponentActivity() {

    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Firestore
        db = FirebaseFirestore.getInstance()

        // Set up buttons
        val btnCompare: Button = findViewById(R.id.btnCompare)
        val btnRecommend: Button = findViewById(R.id.btnRecommend)

        btnCompare.setOnClickListener {
            val intent = Intent(this, CompareActivity::class.java)
            startActivity(intent)
        }

        btnRecommend.setOnClickListener {
            val intent = Intent(this, RecommendationsActivity::class.java)
            startActivity(intent)
        }
    }
}
