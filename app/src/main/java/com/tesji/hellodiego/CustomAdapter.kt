package com.tesji.hellodiego

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.tesji.hellodiego.databinding.ItemJugadoresBinding


class CustomAdapter(private var context: Context, var usuaioss: ArrayList<JugadorArg>?) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    var db = FirebaseFirestore.getInstance()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context)
            .inflate(R.layout.item_jugadores, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        viewHolder: ViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        viewHolder.binding.textView2.text = "nombre: " + usuaioss!![position].nombre
        viewHolder.binding.textView3.text = "estatura: ${usuaioss!![position].estatura}"
        viewHolder.binding.textView4.text = "posicion: " + usuaioss!![position].posicion
        viewHolder.binding.textView5.text = "numero: " + usuaioss!![position].numero
        viewHolder.binding.card.setOnClickListener {
            val bundle = bundleOf(
                "id" to usuaioss!![position].id,
                "nombre" to usuaioss!![position].nombre,
                "estatura" to usuaioss!![position].estatura,
                "posicion" to usuaioss!![position].posicion,
                "numero" to usuaioss!![position].numero
            )

            it.findNavController().navigate(R.id.action_inicio_to_editarBJugador, bundle)
        }
        /*
            viewHolder.borrar.setOnClickListener {

                db.collection("users").document(usuaioss!![position].id.toString())
                    .delete()
                    .addOnSuccessListener {
                        Toast.makeText(context, "se borro", Toast.LENGTH_LONG).show()
                        context.startActivity(Intent(context, Inicio::class.java))

                        notifyDataSetChanged()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(context, "error al guardar $e", Toast.LENGTH_LONG).show()
                    }
            }
            viewHolder.actualizar.setOnClickListener {
                val intent = Intent(context, EditarUsuario::class.java)
                intent.putExtra("nombre", usuaioss!![position].nombre)
                intent.putExtra("telefono", usuaioss!![position].telefono)
                intent.putExtra("correo", usuaioss!![position].correo)
                intent.putExtra("domicilio", usuaioss!![position].domicilio)
                intent.putExtra("id", usuaioss!![position].id)
                context.startActivity(intent)
                //(context as Inicio::class.java).finish()

            }*/
    }

    override fun getItemCount(): Int {
        return usuaioss!!.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemJugadoresBinding.bind(view)
    }
}
