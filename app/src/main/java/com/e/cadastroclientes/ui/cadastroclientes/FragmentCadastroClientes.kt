package com.e.cadastroclientes.ui.cadastroclientes

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.e.cadastroclientes.R
import com.e.cadastroclientes.database.AppDataBase
import com.e.cadastroclientes.models.Cliente
import com.e.cadastroclientes.repository.Repository
import com.e.cadastroclientes.repository.RepositoryImpl
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fragment_cadastro_clientes.*
import kotlinx.android.synthetic.main.fragment_cadastro_clientes.view.*


class FragmentCadastroClientes : Fragment() {

    private lateinit var db: AppDataBase
    private lateinit var repo: Repository

    val viewModel by viewModels<FragmentCadastroClientesViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return FragmentCadastroClientesViewModel(repo) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cadastro_clientes, container, false)


        db = activity?.applicationContext?.let { AppDataBase.invoke(it) }!!
        repo = RepositoryImpl(db.clienteDao())

        //pegando dados do edittext
        val id = 0
        val campoNome : TextInputEditText = view.ed_cadastro_nome
        val campoTelefone = view.ed_cadastro_telefone
        val campoEmail = view.ed_cadastro_email
        val campoEndereco = view.ed_cadastro_endereco
        val campoBairro = view.ed_cadastro_bairro
        val campoCidade = view.ed_cadastro_cidade
        val campoEstado = view.ed_cadastro_estado
        val campoCep = view.ed_cadastro_cep


        view.btn_cadastro_salvar.setOnClickListener {

            //salvando o cliente
            viewModel.addCliente(Cliente(
                nome = campoNome.text.toString(),
                telefone = campoTelefone.text.toString(),
                email = campoEmail.text.toString(),
                endereco = campoEndereco.text.toString(),
                bairro = campoBairro.text.toString(),
                cidade = campoCidade.text.toString(),
                estado = campoEstado.text.toString(),
                cep =  campoCep.text.toString()
            ))




            Toast.makeText(context, "Cadastro efetuado", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_fragmentCadastroClientes_to_fragmentListaClientes)
            //fechar teclado
            it.hideKeyboard()


        }

        view.toolbar_cadastro.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_fragmentCadastroClientes_to_fragmentListaClientes)
            it.hideKeyboard()
        }

        return view
    }


}


fun View.hideKeyboard() {
    val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(windowToken, 0)
}