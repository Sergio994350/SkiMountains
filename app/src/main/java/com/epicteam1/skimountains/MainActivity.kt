package com.epicteam1.skimountains

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity() {

    val tag = "ski_place"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = FirebaseFirestore.getInstance()

        db.collection("ski_place_database")
            .whereEqualTo("entity", "SkiPlace")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d(tag, "${document.id} => ${document.data}")
                    print("${document.data["nameRus"]} ")
                    print("${document.data["regionRus"]} ")
                    println("${document.data["regionBig"]} ")
                    println()
                }
            }
            .addOnFailureListener { exception ->
                Log.w(tag, "Error getting documents: ", exception)
            }

    }
}