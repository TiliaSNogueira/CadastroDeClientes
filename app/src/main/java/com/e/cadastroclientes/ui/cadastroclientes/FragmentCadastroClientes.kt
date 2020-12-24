package com.e.cadastroclientes.ui.cadastroclientes

import android.content.Context
import android.os.Bundle
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
import kotlinx.android.synthetic.main.fragment_cadastro_clientes.*
import kotlinx.android.synthetic.main.fragment_cadastro_clientes.view.*


class FragmentCadastroClientes : Fragment() {

    private lateinit var db: AppDataBase
    private lateinit var repo: Repository

    //está instanciado assim pq a ViewModel tem o Repository como parâmetro
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

        //iniciando db e repo
        db = activity?.applicationContext?.let { AppDataBase.invoke(it) }!!
        repo = RepositoryImpl(db.clienteDao())

        //click no botão cadastrar
        view.btn_cadastro_salvar.setOnClickListener {
            //função que pega os dados e vincula ao cliente
            val clientePreenchido = pegaDadosParaCadastro()

            //confere se os dados principais foram preechidos, ou seja, se true significa efetua o cadastro, se false pede para preencher os dados corretamente
            if (viewModel.confereDados(clientePreenchido.nome, clientePreenchido.telefone, clientePreenchido.email)) {

                //envia o cliente para a viewModel fazer a ação de adicionar ao banco de dados
                viewModel.addCliente(clientePreenchido)

                Toast.makeText(context, "Cadastro efetuado com sucesso", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_fragmentCadastroClientes_to_fragmentListaClientes)
                it.hideKeyboard()

            } else {
                Toast.makeText(context, "Preencher nome, telefone e email corretamente", Toast.LENGTH_SHORT).show()
            }

        }


        //click na setinha voltar
        view.toolbar_cadastro.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_fragmentCadastroClientes_to_fragmentListaClientes)
            it.hideKeyboard()
        }

        return view
    }


    //função que prga os dados digitados, vincula ao cliente e retorna o cliente com os dados
    private fun pegaDadosParaCadastro(): Cliente {
        //pega os dados digitados
        val nome = ed_cadastro_nome.text.toString()
        val telefone = ed_cadastro_telefone.text.toString()
        val campoEmail = ed_cadastro_email.text.toString()
        val campoEndereco = ed_cadastro_endereco.text.toString()
        val campoBairro = ed_cadastro_bairro.text.toString()
        val campoCidade = ed_cadastro_cidade.text.toString()
        val campoEstado = ed_cadastro_estado.text.toString()
        val campoCep = ed_cadastro_cep.text.toString()

        //vincula os dados ao cliente que está sendo cadastrado
        val cliente = Cliente(
            id = 0,
            nome = nome,
            telefone = telefone,
            email = campoEmail,
            endereco = campoEndereco,
            bairro = campoBairro,
            cidade = campoCidade,
            estado = campoEstado,
            cep = campoCep
        )
        return cliente
    }

    //funçãoo para fechar o teclado
    fun View.hideKeyboard() {
        val inputManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }
}

