package com.e.cadastroclientes.ui.cadastroclientes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        val nome = view.ed_cadastro_nome.text.toString()
        val telefone = view.ed_cadastro_telefone.text.toString()
        val email = view.ed_cadastro_email.text.toString()
        val endereco = view.ed_cadastro_endereco.text.toString()
        val bairro = view.ed_cadastro_bairro.text.toString()
        val cidade = view.ed_cadastro_cidade.text.toString()
        val estado = view.ed_cadastro_estado.text.toString()
        val cep = view.ed_cadastro_cep.text.toString()


        //vinculando os dados ao cliente
        val clienteCadastrado = Cliente(
            id,
            nome,
            telefone,
            email,
            endereco,
            bairro,
            cidade,
            estado,
            cep
        )


        view.btn_cadastro_salvar.setOnClickListener {
            //salvando o cliente
            viewModel.addCliente(clienteCadastrado)
            Log.i("CADASTRO", clienteCadastrado.toString())
            Toast.makeText(context, "Cadastro efetuado", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_fragmentCadastroClientes_to_fragmentListaClientes)
        }

        view.toolbar_cadastro.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_fragmentCadastroClientes_to_fragmentListaClientes)
        }

        return view
    }


}