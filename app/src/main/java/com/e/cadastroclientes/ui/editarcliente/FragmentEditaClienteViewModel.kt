package com.e.cadastroclientes.ui.editarcliente

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.cadastroclientes.models.Cliente
import com.e.cadastroclientes.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FragmentEditaClienteViewModel (val repository: Repository) : ViewModel() {

    fun atualizaCliente(cliente: Cliente) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.atualizaClienteTask(cliente)
        }
    }


}