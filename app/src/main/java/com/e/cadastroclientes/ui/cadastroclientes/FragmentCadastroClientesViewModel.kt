package com.e.cadastroclientes.ui.cadastroclientes

import android.text.TextUtils
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.cadastroclientes.models.Cliente
import com.e.cadastroclientes.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FragmentCadastroClientesViewModel(val repository: Repository) : ViewModel() {

    //antes de adcionar ao banco de dados, conferimos se os dados foram preenchidos ou não
    fun addCliente(cliente: Cliente): Boolean {
        if (confereDados(
                cliente.nome,
                cliente.telefone,
                cliente.email,
                cliente.endereco,
                cliente.bairro,
                cliente.cidade,
                cliente.estado,
                cliente.cep
            )
        ) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.addClienteTask(cliente)
            }
            return true
        } else {
            return false
        }
    }

    //função que confere se os campos foram preenchidos (nome, telefone e emailsão obrigatórios, os outros campos são opcionais)
    private fun confereDados(
        nome: String,
        telefone: String,
        email: String,
        endereco: String,
        bairro: String,
        cidade: String,
        estado: String,
        cep: String
    ): Boolean {
        return !(TextUtils.isEmpty(nome) || TextUtils.isEmpty(telefone) || TextUtils.isEmpty(email) && TextUtils.isEmpty(
            endereco
        ) && TextUtils.isEmpty(bairro) && TextUtils.isEmpty(cidade) && TextUtils.isEmpty(estado) && TextUtils.isEmpty(
            cep
        ))

    }

}