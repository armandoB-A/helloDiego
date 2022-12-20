package com.tesji.hellodiego

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity() {
    var db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create a new user with a first and last name
        // Create a new user with a first and last name
        val user: MutableMap<String, Any> = HashMap()
        user["first"] = "Ada"
        user["last"] = "Lovelace"
        user["born"] = 1815

        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->

            }
            .addOnFailureListener { e -> }
    }


}

// funciones  que retornan valores
fun convercionPesosDolar(catPesos: Double): Double {
    return (catPesos / 20.13)

}

//funciones lambda (funciones de flecha)
var calcularEdad = { anioNacimiento: Int, anioActual: Int ->
    var edad: Int
    edad = anioActual - anioNacimiento
    if (edad >= 18) {
        println("tu edad es  $edad ")
    } else {
        println("tu edad es $edad  ")

    }
}


