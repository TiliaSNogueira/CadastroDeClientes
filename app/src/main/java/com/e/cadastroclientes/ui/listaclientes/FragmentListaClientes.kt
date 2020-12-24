package com.e.cadastroclientes.ui.listaclientes

import android.os.Bundle
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
import com.e.cadastroclientes.repository.Repository
import com.e.cadastroclientes.repository.RepositoryImpl
import kotlinx.android.synthetic.main.fragment_lista_clientes.view.*

class FragmentListaClientes : Fragment() {

    private lateinit var db: AppDataBase
    private lateinit var repo: Repository

    //está instanciado assim pq a ViewModel tem o Repository como parâmetro
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

        //iniciando db e repo
        db = activity?.applicationContext?.let { AppDataBase.invoke(it) }!!
        repo = RepositoryImpl(db.clienteDao())


        //configurando recyclerview
        val adapter = ListaClientesAdapter()
        view.rv_frag_lista_clientes.adapter = adapter
        view.rv_frag_lista_clientes.layoutManager = LinearLayoutManager(context)
        view.rv_frag_lista_clientes.setHasFixedSize(true)

        viewModel.listaDeClientes.observe(viewLifecycleOwner, {
            adapter.setData(it)
        })

        //essa chamada cria alguns clientes para serem pré exibidos para poder testar os comportamentos de update e delete
        //toda vez que volta para a lista essa função é chamada novamente, então os 3 contatos prepreechidos ficam repetidos... infelizmente não consegui corrigir a tempo
        viewModel.prePreencheClientesExibicao()

        //viewModel chama a lista e passa pata o adapter
        viewModel.getAllClientes()


        //click no botao de cadatrar novo usuario
        view.btn_cadastrar_frag_lista_clientes.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentListaClientes_to_fragmentCadastroClientes)
        }

        return view
    }


}

