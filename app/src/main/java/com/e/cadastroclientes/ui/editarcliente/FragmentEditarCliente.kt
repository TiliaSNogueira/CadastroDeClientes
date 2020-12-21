package com.e.cadastroclientes.ui.editarcliente

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.e.cadastroclientes.R
import com.e.cadastroclientes.database.AppDataBase
import com.e.cadastroclientes.models.Cliente
import com.e.cadastroclientes.repository.Repository
import com.e.cadastroclientes.repository.RepositoryImpl
import com.e.cadastroclientes.ui.cadastroclientes.FragmentCadastroClientesViewModel
import com.e.cadastroclientes.ui.detallhescliente.FragmentDetalhesClienteArgs
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fragment_cadastro_clientes.view.*
import kotlinx.android.synthetic.main.fragment_detalhes_cliente.view.*
import kotlinx.android.synthetic.main.fragment_editar_cliente.*
import kotlinx.android.synthetic.main.fragment_editar_cliente.view.*


class FragmentEditarCliente : Fragment() {

    private lateinit var db: AppDataBase
    private lateinit var repo: Repository

    private val args by navArgs<FragmentEditarClienteArgs>()

    val viewModel by viewModels<FragmentEditaClienteViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return FragmentEditaClienteViewModel(repo) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_editar_cliente, container, false)

        db = activity?.applicationContext?.let { AppDataBase.invoke(it) }!!
        repo = RepositoryImpl(db.clienteDao())

        view.toolbar_editar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_fragmentEditarCliente_to_fragmentListaClientes)
        }



        view.ed_editar_nome.setText(args.clienteObj.nome)
        view.ed_editar_email.setText(args.clienteObj.email)
        view.ed_editar_telefone.setText(args.clienteObj.telefone)
        view.ed_editar_email.setText(args.clienteObj.email)
        view.ed_editar_endereco.setText(args.clienteObj.endereco)
        view.ed_editar_bairro.setText(args.clienteObj.bairro)
        view.ed_editar_cidade.setText(args.clienteObj.cidade)
        view.ed_editar_estado.setText(args.clienteObj.estado)
        view.ed_editar_cep.setText(args.clienteObj.cep)


        view.btn_editar_salvar.setOnClickListener {
            //editar
            atualizaClienteSelecionado()
            //voltar para a lista
            findNavController().navigate(R.id.action_fragmentEditarCliente_to_fragmentListaClientes)
            Toast.makeText(context, "ATUALIZADO COM SUCESSO", Toast.LENGTH_SHORT).show()

        }

        return view
    }

    private fun atualizaClienteSelecionado() {
        val id = 0
        val campoNome: TextInputEditText = ed_editar_nome
        val campoTelefone = ed_editar_telefone
        val campoEmail = ed_editar_email
        val campoEndereco = ed_editar_endereco
        val campoBairro = ed_editar_bairro
        val campoCidade = ed_editar_cidade
        val campoEstado = ed_editar_estado
        val campoCep = ed_editar_cep

        viewModel.atualizaCliente(
            Cliente(
                id = args.clienteObj.id,
                nome = campoNome.text.toString(),
                telefone = campoTelefone.text.toString(),
                email = campoEmail.text.toString(),
                endereco = campoEndereco.text.toString(),
                bairro = campoBairro.text.toString(),
                cidade = campoCidade.text.toString(),
                estado = campoEstado.text.toString(),
                cep = campoCep.text.toString()
            )
        )
    }


}