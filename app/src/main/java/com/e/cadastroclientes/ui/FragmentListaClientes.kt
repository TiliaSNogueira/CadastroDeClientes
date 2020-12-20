package com.e.cadastroclientes.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.cadastroclientes.R
import com.e.cadastroclientes.models.Cliente
import kotlinx.android.synthetic.main.fragment_lista_clientes.view.*

class FragmentListaClientes : Fragment(), ListaClientesAdapter.clienteOnClickListener {

    private var listaClientes: MutableList<Cliente> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_lista_clientes, container, false)


        listaClientes = mutableListOf(
            Cliente(10, "Tilia", 97990, "tilia", "apinajes", "perdizes", "sp", "SP", 2 - 8555),
            Cliente(10, "Livia", 97990, "tilia", "apinajes", "perdizes", "sp", "SP", 2 - 8555),
            Cliente(10, "Milaide", 97990, "tilia", "apinajes", "perdizes", "sp", "SP", 2 - 8555),
            Cliente(10, "Dude", 97990, "tilia", "apinajes", "perdizes", "sp", "SP", 2 - 8555),

            )

        //configurando o adapter
        view.rv_frag_lista_clientes.adapter = ListaClientesAdapter(listaClientes, this)
        view.rv_frag_lista_clientes.layoutManager = LinearLayoutManager(context)
        view.rv_frag_lista_clientes.setHasFixedSize(true)


        view.btn_cadastrar_frag_lista_clientes.setOnClickListener{
            findNavController().navigate(R.id.action_fragmentListaClientes_to_fragmentCadastroClientes)
        }





        return view
    }

    override fun selecionaClienteClick(position: Int) {
        val clienteSelecionado = listaClientes[position]

        val direction =
            FragmentListaClientesDirections.actionFragmentListaClientesToFragmentDetalhesCliente(
                clienteSelecionado
            )
        findNavController().navigate(direction)
    }


}