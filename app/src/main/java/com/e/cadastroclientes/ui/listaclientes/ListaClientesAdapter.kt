package com.e.cadastroclientes.ui.listaclientes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.e.cadastroclientes.R
import com.e.cadastroclientes.models.Cliente
import kotlinx.android.synthetic.main.item_cliente.view.*

class ListaClientesAdapter(
    val listaClientes: List<Cliente>,
    val listener: clienteOnClickListener
) :
    RecyclerView.Adapter<ListaClientesAdapter.ClienteViewHolder>() {

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
            listener.selecionaClienteClick(position)
        }

    }

    override fun getItemCount() = listaClientes.size


    interface clienteOnClickListener {
        fun selecionaClienteClick(position: Int)

    }

    class ClienteViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        val nomeCliente: TextView = item.tv_item_nome
        val telefoneCliente: TextView = item.tv_item_telefone
        val emailCliente: TextView = item.tv_item_email

    }


}
