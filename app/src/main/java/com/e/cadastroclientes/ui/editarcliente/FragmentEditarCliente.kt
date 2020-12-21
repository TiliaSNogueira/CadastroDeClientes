package com.e.cadastroclientes.ui.editarcliente

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.e.cadastroclientes.R
import com.e.cadastroclientes.ui.detallhescliente.FragmentDetalhesClienteArgs
import kotlinx.android.synthetic.main.fragment_detalhes_cliente.view.*
import kotlinx.android.synthetic.main.fragment_editar_cliente.view.*


class FragmentEditarCliente : Fragment() {

    private val args by navArgs<FragmentEditarClienteArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_editar_cliente, container, false)

        view.ed_editar_nome.setText(args.clienteObj.nome)
        view.ed_editar_email.setText(args.clienteObj.email)
        view.ed_editar_telefone.setText(args.clienteObj.telefone)
        view.ed_editar_email.setText(args.clienteObj.email)
        view.ed_editar_endereco.setText(args.clienteObj.endereco)
        view.ed_editar_bairro.setText(args.clienteObj.bairro)
        view.ed_editar_cidade.setText(args.clienteObj.cidade)
        view.ed_editar_estado.setText(args.clienteObj.estado)
        view.ed_editar_cep.setText(args.clienteObj.cep)

        return view
    }
}