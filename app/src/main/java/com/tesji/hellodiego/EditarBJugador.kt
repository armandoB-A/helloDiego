package com.tesji.hellodiego

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.tesji.hellodiego.databinding.FragmentEditarBJugadorBinding


class EditarBJugador : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val db = FirebaseFirestore.getInstance()
        val binding = FragmentEditarBJugadorBinding.inflate(layoutInflater)

        val claveId: String = arguments?.getString("id").toString()

        binding.nom.setText(arguments?.getString("nombre"))
        binding.est.setText(arguments?.getString("estatura"))
        binding.positions.setText(arguments?.getString("posicion"))
        binding.numjs.setText(arguments?.getString("numero"))


        binding.button.setOnClickListener {
            val jugador = JugadorArg(
                claveId,
                binding.nom.text.toString(),
                binding.est.text.toString(),
                binding.positions.text.toString(),
                binding.numjs.text.toString()
            )
            db.collection("jugadores").document(claveId).set(jugador)
                .addOnSuccessListener {
                    Toast.makeText(
                        binding.root.context,
                        "Jugador actualizado con exito",
                        Toast.LENGTH_SHORT
                    )
                        .show()
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
        binding.button2.setOnClickListener {
            db.collection("jugadores").document(claveId)
                .delete()
                .addOnSuccessListener {
                    Toast.makeText(binding.root.context, "se borro" + claveId, Toast.LENGTH_LONG)
                        .show()
                    activity?.onBackPressed()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(binding.root.context, "error al borrar $e", Toast.LENGTH_LONG)
                        .show()
                }
        }
        return binding.root
    }

}