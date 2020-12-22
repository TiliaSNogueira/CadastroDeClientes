package com.e.cadastroclientes.ui.detallhescliente

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.cadastroclientes.models.Cliente
import com.e.cadastroclientes.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.invoke
import kotlinx.coroutines.launch

class FragmentDetalhesClienteViewModel (val repository: Repository) : ViewModel() {

    fun deleteCliente(cliente: Cliente){
        viewModelScope.launch(Dispatchers.IO) {
        repository.deleteClienteTask(cliente)
        }
    }
}