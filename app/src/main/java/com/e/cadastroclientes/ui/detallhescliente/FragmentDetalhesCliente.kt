package com.e.cadastroclientes.ui.detallhescliente

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.e.cadastroclientes.R
import kotlinx.android.synthetic.main.fragment_detalhes_cliente.view.*

class FragmentDetalhesCliente : Fragment() {

        private val args by navArgs<FragmentDetalhesClienteArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detalhes_cliente, container, false)

        val clienteExibido = args.clienteObj
            view.tv_detalhes_nome.setText( clienteExibido.nome)
            view.tv_detalhes_email.setText( clienteExibido.email)
            view.tv_detalhes_telefone.setText( clienteExibido.telefone)
            view.tv_detalhes_email.setText( clienteExibido.email)
            view.tv_detalhes_endereco.setText( clienteExibido.endereco)
            view.tv_detalhes_bairro.setText( clienteExibido.bairro)
            view.tv_detalhes_cidade.setText( clienteExibido.cidade)
            view.tv_detalhes_estado.setText( clienteExibido.estado)
            view.tv_detalhes_cep.setText( clienteExibido.cep)

        view.toolbar_frag_detalhes_clientes.inflateMenu(R.menu.menu_editar);
        view.toolbar_frag_detalhes_clientes.setOnMenuItemClickListener {
            val action = FragmentDetalhesClienteDirections.actionFragmentDetalhesClienteToFragmentEditarCliente(clienteExibido)
            findNavController().navigate(action)
            true
        }


       view.toolbar_frag_detalhes_clientes.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_fragmentDetalhesCliente_to_fragmentListaClientes)
        }


        return view
    }




}