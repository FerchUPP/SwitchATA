package com.example.transicionapple.firestore

import android.util.Log
import com.example.transicionapple.models.iPhoneModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects

object FirestoreHelper {
    private const val COLLECTION_NAME = "iPhoneModels"

    fun addiPhoneModel(db: FirebaseFirestore, model: iPhoneModel, onSuccess: (String) -> Unit, onFailure: (Exception) -> Unit) {
        db.collection(COLLECTION_NAME)
            .add(model)
            .addOnSuccessListener { documentReference ->
                onSuccess(documentReference.id)
            }
            .addOnFailureListener { e ->
                onFailure(e)
            }
    }

    fun getAlliPhoneModels(db: FirebaseFirestore, onSuccess: (List<iPhoneModel>) -> Unit, onFailure: (Exception) -> Unit) {
        db.collection(COLLECTION_NAME)
            .get()
            .addOnSuccessListener { result ->
                val models = result.toObjects<iPhoneModel>()
                Log.d("FirestoreHelper", "Retrieved models: $models")
                onSuccess(models)
            }
            .addOnFailureListener { exception ->
                Log.w("FirestoreHelper", "Error getting documents.", exception)
                onFailure(exception)
            }
    }

    fun updateiPhoneModel(db: FirebaseFirestore, documentId: String, updatedData: Map<String, Any>, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        db.collection(COLLECTION_NAME).document(documentId)
            .update(updatedData)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { e ->
                onFailure(e)
            }
    }

    fun deleteiPhoneModel(db: FirebaseFirestore, documentId: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        db.collection(COLLECTION_NAME).document(documentId)
            .delete()
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { e ->
                onFailure(e)
            }
    }
}