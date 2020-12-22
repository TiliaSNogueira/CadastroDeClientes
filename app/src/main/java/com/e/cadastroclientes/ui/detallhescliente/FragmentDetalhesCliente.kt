package com.e.cadastroclientes.ui.detallhescliente

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.e.cadastroclientes.R
import com.e.cadastroclientes.database.AppDataBase
import com.e.cadastroclientes.repository.Repository
import com.e.cadastroclientes.repository.RepositoryImpl
import com.e.cadastroclientes.ui.listaclientes.FragmentListaClientesViewModel
import kotlinx.android.synthetic.main.fragment_detalhes_cliente.*
import kotlinx.android.synthetic.main.fragment_detalhes_cliente.view.*

class FragmentDetalhesCliente : Fragment() {

    private val args by navArgs<FragmentDetalhesClienteArgs>()

    private lateinit var db: AppDataBase
    private lateinit var repo: Repository

    val viewModel by viewModels<FragmentDetalhesClienteViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return FragmentDetalhesClienteViewModel(repo) as T
            }
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detalhes_cliente, container, false)

        db = activity?.applicationContext?.let { AppDataBase.invoke(it) }!!
        repo = RepositoryImpl(db.clienteDao())



        val clienteExibido = args.clienteObj
        view.tv_detalhes_nome.setText(clienteExibido.nome)
        view.tv_detalhes_email.setText(clienteExibido.email)
        view.tv_detalhes_telefone.setText(clienteExibido.telefone)
        view.tv_detalhes_email.setText(clienteExibido.email)
        view.tv_detalhes_endereco.setText(clienteExibido.endereco)
        view.tv_detalhes_bairro.setText(clienteExibido.bairro)
        view.tv_detalhes_cidade.setText(clienteExibido.cidade)
        view.tv_detalhes_estado.setText(clienteExibido.estado)
        view.tv_detalhes_cep.setText(clienteExibido.cep)

        view.toolbar_frag_detalhes_clientes.inflateMenu(R.menu.menu_opcoes_editar_deletar);


        view.toolbar_frag_detalhes_clientes.setOnMenuItemClickListener(
            fun(item: MenuItem): Boolean {
                val id = item.itemId
                return if (id == R.id.cadastro_menu_editar) {
                    val action =
                        FragmentDetalhesClienteDirections.actionFragmentDetalhesClienteToFragmentEditarCliente(
                            clienteExibido
                        )
                    findNavController().navigate(action)
                    true
                } else {
                    val builder = AlertDialog.Builder(requireContext())
                    builder.setPositiveButton("Sim") { _, _ ->
                        viewModel.deleteCliente(args.clienteObj)
                        Toast.makeText(context, "Cliente deletado com sucesso", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_fragmentDetalhesCliente_to_fragmentListaClientes)
                    }
                    builder.setNegativeButton("Não") { _, _ -> }

                    builder.setTitle("Deletar as informações do cliente ${args.clienteObj.nome}?")
                    builder.create().show()

                    true
                }
            }
        )




        view.toolbar_frag_detalhes_clientes.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_fragmentDetalhesCliente_to_fragmentListaClientes)
        }


        return view
    }




}