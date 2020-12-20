package com.e.cadastroclientes.ui.listaclientes

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.cadastroclientes.R
import com.e.cadastroclientes.database.AppDataBase
import com.e.cadastroclientes.models.Cliente
import com.e.cadastroclientes.repository.Repository
import com.e.cadastroclientes.repository.RepositoryImpl
import kotlinx.android.synthetic.main.fragment_lista_clientes.view.*

class FragmentListaClientes : Fragment(), ListaClientesAdapter.clienteOnClickListener {

   private var listaClientes: List<Cliente> = listOf()

    private lateinit var db: AppDataBase
    private lateinit var repo: Repository

    val viewModel by viewModels<FragmentListaClientesViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return FragmentListaClientesViewModel(repo) as T
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_lista_clientes, container, false)

        db = activity?.applicationContext?.let { AppDataBase.invoke(it) }!!
        repo = RepositoryImpl(db.clienteDao())



        viewModel.addCliente(Cliente(nome = "Tilia", telefone = 1234, endereco = "Rua Apinajés", email = "tilia@", cep = 5643354, estado = "SP", bairro = "Perdizes", cidade = "São Paulo"))

        viewModel.getAllClientes()

        viewModel.listaDeClientes.observe(viewLifecycleOwner, {
            Log.i("LISTA", it.toString())

        })

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
        Log.i("LISTACLIENTES", clienteSelecionado.toString())

        val direction =
            FragmentListaClientesDirections.actionFragmentListaClientesToFragmentDetalhesCliente(
                clienteSelecionado
            )
        findNavController().navigate(direction)
    }


}