package com.e.cadastroclientes.ui.listaclientes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.cadastroclientes.models.Cliente
import com.e.cadastroclientes.repository.Repository
import kotlinx.coroutines.launch

class FragmentListaClientesViewModel(val repository: Repository) : ViewModel() {


    val listaDeClientes = MutableLiveData<List<Cliente>>()

    fun getAllClientes(){
        viewModelScope.launch {
            listaDeClientes.value = repository.getAllClientesTask()
        }
    }

    fun addCliente(cliente: Cliente){
        viewModelScope.launch {
            repository.addClienteTask(cliente)
        }
    }


}