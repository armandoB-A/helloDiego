package com.tesji.hellodiego

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.tesji.hellodiego.databinding.FragmentInicioBinding


class Inicio : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding=FragmentInicioBinding.inflate(layoutInflater)
        val db = FirebaseFirestore.getInstance()

        db.collection("jugadores").get().addOnSuccessListener { it ->
            var materias = arrayListOf<JugadorArg>()
            it.documents.forEach { its ->
                materias.add(
                    JugadorArg(
                        its.get("id").toString(),
                        its.get("nombre").toString(),
                        its.get("estatura").toString(),
                        its.get("posicion").toString(),
                        its.get("numero").toString()
                    )
                )
            }
            var customAdapter=CustomAdapter(binding.root.context, materias)
            binding.recicler.adapter=customAdapter
            val layoutManager = GridLayoutManager(binding.root.context, 2)
            binding.recicler.layoutManager=layoutManager

        }
            .addOnFailureListener {
                Toast.makeText(binding.root.context, "no se pudo", Toast.LENGTH_SHORT).show()
            }
        binding.button.setOnClickListener {
            it.findNavController().navigate(R.id.action_inicio_to_agregarJugador)
        }
        return binding.root
    }

}