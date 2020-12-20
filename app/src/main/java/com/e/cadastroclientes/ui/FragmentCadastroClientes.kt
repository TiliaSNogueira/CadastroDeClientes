package com.e.cadastroclientes.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.e.cadastroclientes.R
import kotlinx.android.synthetic.main.fragment_cadastro_clientes.view.*


class FragmentCadastroClientes : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cadastro_clientes, container, false)

        view.btn_cadastro_salvar.setOnClickListener{
            Toast.makeText(context, "Cadastro efetuado", Toast.LENGTH_SHORT).show()
        }

        view.toolbar_cadastro.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_fragmentCadastroClientes_to_fragmentListaClientes)
        }

        return view
    }


}