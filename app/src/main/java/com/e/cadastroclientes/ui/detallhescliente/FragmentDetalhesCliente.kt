package com.e.cadastroclientes.ui.detallhescliente

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
import kotlinx.android.synthetic.main.fragment_detalhes_cliente.view.*

class FragmentDetalhesCliente : Fragment() {

    private val args by navArgs<FragmentDetalhesClienteArgs>()

    private lateinit var db: AppDataBase
    private lateinit var repo: Repository

    //está instanciado assim pq a ViewModel tem o Repository como parâmetro
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

        //iniciando db e repo
        db = activity?.applicationContext?.let { AppDataBase.invoke(it) }!!
        repo = RepositoryImpl(db.clienteDao())

        //infla o menus de opções na toolbar (edita e remove)
        view.toolbar_frag_detalhes_clientes.inflateMenu(R.menu.menu_opcoes_editar_deletar);


        //carrega os dados do objeto cliente que veio no safe args para exibir nos campos
        view.tv_detalhes_nome.text = args.clienteObj.nome
        view.tv_detalhes_email.text = args.clienteObj.email
        view.tv_detalhes_telefone.text = args.clienteObj.telefone
        view.tv_detalhes_email.text = args.clienteObj.email
        view.tv_detalhes_endereco.text = args.clienteObj.endereco
        view.tv_detalhes_bairro.text = args.clienteObj.bairro
        view.tv_detalhes_cidade.text = args.clienteObj.cidade
        view.tv_detalhes_estado.text = args.clienteObj.estado
        view.tv_detalhes_cep.text = args.clienteObj.cep


        //como temos 2 ícones na toolbar, temos que verificar qual deles foi clicado, e então atribuir o comportamento de cada um
        view.toolbar_frag_detalhes_clientes.setOnMenuItemClickListener(
            fun(item: MenuItem): Boolean {
                val id = item.itemId
                //se click ocorreu no icone editar, navegamos até o fragment que edita o cliente e enviamos o mesmo objeto cliente pelo safe args para ser editado
                return if (id == R.id.cadastro_menu_editar) {
                    val action =
                        FragmentDetalhesClienteDirections.actionFragmentDetalhesClienteToFragmentEditarCliente(
                            args.clienteObj
                        )
                    findNavController().navigate(action)
                    true

                } else {
                    //se o click ocorreu no icone deletar, abrimos uma caixa de dialogo para confirmar a exclusão
                    val builder = AlertDialog.Builder(requireContext())
                    builder.setPositiveButton("Sim") { _, _ ->
                        viewModel.deleteCliente(args.clienteObj)
                        Toast.makeText(context, "Cliente deletado com sucesso", Toast.LENGTH_SHORT)
                            .show()
                        findNavController().navigate(R.id.action_fragmentDetalhesCliente_to_fragmentListaClientes)
                    }
                    builder.setNegativeButton("Não") { _, _ -> }

                    builder.setTitle("Deletar as informações do cliente ${args.clienteObj.nome}?")
                    builder.create().show()

                    true
                }
            }
        )


        //click na setinha de voltar
        view.toolbar_frag_detalhes_clientes.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_fragmentDetalhesCliente_to_fragmentListaClientes)
        }

        return view
    }

}