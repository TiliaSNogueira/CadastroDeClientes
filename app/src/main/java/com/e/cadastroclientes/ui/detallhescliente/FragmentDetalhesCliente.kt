package com.e.cadastroclientes.ui.detallhescliente

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.e.cadastroclientes.R
import kotlinx.android.synthetic.main.fragment_detalhes_cliente.view.*

class FragmentDetalhesCliente : Fragment() {

    val args: FragmentDetalhesClienteArgs by navArgs()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detalhes_cliente, container, false)

        val clienteExibido = args.objCliente

        view.tv_detalhes_nome.text = clienteExibido.nome
        view.tv_detalhes_email.text = clienteExibido.email
        view.tv_detalhes_telefone.text = clienteExibido.telefone.toString()
        view.tv_detalhes_email.text = clienteExibido.email
        view.tv_detalhes_endereco.text = clienteExibido.endereco
        view.tv_detalhes_bairro.text = clienteExibido.bairro
        view.tv_detalhes_cidade.text = clienteExibido.cidade
        view.tv_detalhes_estado.text = clienteExibido.estado
        view.tv_detalhes_cep.text = clienteExibido.cep.toString()

       view.toolbar_frag_detalhes_clientes.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_fragmentDetalhesCliente_to_fragmentListaClientes)
        }


        return view



    }




}