package com.tesji.hellodiego

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore
import com.tesji.hellodiego.databinding.FragmentAgregarJugadorBinding


class AgregarJugador : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentAgregarJugadorBinding.inflate(layoutInflater)
        val db = FirebaseFirestore.getInstance()
        binding.button.setOnClickListener {
            val user = JugadorArg(
                binding.clave.text.toString(),
                binding.nom.text.toString(),
                binding.est.text.toString(),
                binding.positions.text.toString(),
                binding.numjs.text.toString()
            )
            db.collection("jugadores").document(binding.clave.text.toString()).set(user)
                .addOnSuccessListener {
                    Toast.makeText(
                        binding.root.context,
                        "Jugador agregado con exito",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    binding.clave.setText("")
                    binding.nom.setText("")
                    binding.est.setText("")
                    binding.positions.setText("")
                    binding.numjs.setText("")
                }.addOnFailureListener {
                    Toast.makeText(
                        binding.root.context,
                        "El jugador no se guardo",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
        }
        return binding.root
    }

}