package com.e.cadastroclientes.ui.listaclientes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.cadastroclientes.models.Cliente
import com.e.cadastroclientes.repository.Repository
import kotlinx.coroutines.launch

class FragmentListaClientesViewModel(val repository: Repository) : ViewModel() {

    //lista que será passada para o adapter
    val listaDeClientes = MutableLiveData<List<Cliente>>()

    //função que passa o value para a lista que vai ser passada parao adapter
    fun getAllClientes() {
        viewModelScope.launch {
            listaDeClientes.value = repository.getAllClientesTask()
        }
    }

}