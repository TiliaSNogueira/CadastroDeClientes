package com.e.cadastroclientes.ui.detallhescliente

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.e.cadastroclientes.R
import com.e.cadastroclientes.models.Cliente
import kotlinx.android.synthetic.main.fragment_detalhes_cliente.view.*

class FragmentDetalhesCliente : Fragment() {

        private val args by navArgs<FragmentDetalhesClienteArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detalhes_cliente, container, false)


            view.tv_detalhes_nome.setText(args.clienteObj.nome)
            view.tv_detalhes_email.setText(args.clienteObj.email)
            view.tv_detalhes_telefone.setText(args.clienteObj.telefone)
            view.tv_detalhes_email.setText(args.clienteObj.email)
            view.tv_detalhes_endereco.setText(args.clienteObj.endereco)
            view.tv_detalhes_bairro.setText(args.clienteObj.bairro)
            view.tv_detalhes_cidade.setText(args.clienteObj.cidade)
            view.tv_detalhes_estado.setText(args.clienteObj.estado)
            view.tv_detalhes_cep.setText(args.clienteObj.cep)



       view.toolbar_frag_detalhes_clientes.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_fragmentDetalhesCliente_to_fragmentListaClientes)
        }


        return view
    }

}