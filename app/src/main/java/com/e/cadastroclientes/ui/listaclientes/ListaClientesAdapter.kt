package com.e.cadastroclientes.ui.listaclientes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.e.cadastroclientes.R
import com.e.cadastroclientes.models.Cliente
import kotlinx.android.synthetic.main.item_cliente.view.*

class ListaClientesAdapter() : RecyclerView.Adapter<ListaClientesAdapter.ClienteViewHolder>(){

    var listaClientes = emptyList<Cliente>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClienteViewHolder {
        //infla o layout do item
        return ClienteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_cliente, parent, false)
        )
    }

    //pega dados dos clientes da lista para mostrar em cada item consecutivamente
    override fun onBindViewHolder(holder: ClienteViewHolder, position: Int) {
        val cliente = listaClientes[position]
        holder.nomeCliente.text = cliente.nome
        holder.telefoneCliente.text = cliente.telefone.toString()
        holder.emailCliente.text = cliente.email

        //evento de clique no item da lista navega até o fragment que exibe os detalhes do cliente
        holder.itemView.setOnClickListener {
            val action =
                FragmentListaClientesDirections.actionFragmentListaClientesToFragmentDetalhesCliente(
                    cliente
                )
            holder.itemView.findNavController().navigate(action)
        }

    }

    //pega a quantidade de itens da lista
    override fun getItemCount() = listaClientes.size

    //passa as listas e avisa o adapter quando tem mudanças
    fun setData(cliente: List<Cliente>) {
        this.listaClientes = cliente
        notifyDataSetChanged()

    }

    //classe que pega os dados e mostra no layout
    class ClienteViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val nomeCliente: TextView = item.tv_item_nome
        val telefoneCliente: TextView = item.tv_item_telefone
        val emailCliente: TextView = item.tv_item_email

    }


}
