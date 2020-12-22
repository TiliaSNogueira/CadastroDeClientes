package com.e.cadastroclientes.ui.listaclientes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.e.cadastroclientes.R
import com.e.cadastroclientes.models.Cliente
import kotlinx.android.synthetic.main.item_cliente.view.*

class ListaClientesAdapter() : RecyclerView.Adapter<ListaClientesAdapter.ClienteViewHolder>() {

    var listaClientes = emptyList<Cliente>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClienteViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_cliente, parent, false)
        return ClienteViewHolder(item)
    }

    override fun onBindViewHolder(holder: ClienteViewHolder, position: Int) {
        val cliente = listaClientes[position]

        holder.nomeCliente.text = cliente.nome
        holder.telefoneCliente.text = cliente.telefone.toString()
        holder.emailCliente.text = cliente.email

        holder.itemView.setOnClickListener {
            val action = FragmentListaClientesDirections.actionFragmentListaClientesToFragmentDetalhesCliente(cliente)
            holder.itemView.findNavController().navigate(action)
        }


    }

    override fun getItemCount() = listaClientes.size

    fun setData(cliente : List<Cliente>){
        this.listaClientes = cliente
        notifyDataSetChanged()
    }

    class ClienteViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        val nomeCliente: TextView = item.tv_item_nome
        val telefoneCliente: TextView = item.tv_item_telefone
        val emailCliente: TextView = item.tv_item_email

    }


}
