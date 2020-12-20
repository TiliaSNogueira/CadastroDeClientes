package com.e.cadastroclientes.ui.cadastroclientes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.cadastroclientes.models.Cliente
import com.e.cadastroclientes.repository.Repository
import kotlinx.coroutines.launch

class FragmentCadastroClientesViewModel(val repository: Repository) : ViewModel() {


    val listaDeClientes = MutableLiveData<List<Cliente>>()

    fun addCliente(cliente: Cliente) {
        viewModelScope.launch {
            repository.addClienteTask(cliente)
        }
    }

}