package com.e.cadastroclientes.ui.listaclientes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.cadastroclientes.models.Cliente
import com.e.cadastroclientes.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FragmentListaClientesViewModel(val repository: Repository) : ViewModel() {

    //lista que será passada para o adapter
    val listaDeClientes = MutableLiveData<List<Cliente>>()

    //função que passa o value para a lista que vai ser passada parao adapter
    fun getAllClientes() {
        viewModelScope.launch(Dispatchers.Main){
            listaDeClientes.value = repository.getAllClientesTask()
        }
    }

    fun prePreencheClientesExibicao() {
        val listaPrePreenche = listOf(
            Cliente(
                nome = "Tília S. Nogueira",
                telefone = "979908445",
                email = "tilianogueira@gmail.com",
                endereco = "Rua Apinajés, 385",
                bairro = "Perdizes",
                cidade = "São Paulo",
                estado = "SP",
                cep = "05017000"
            ),
            Cliente(
                nome = "Bill Withers",
                telefone = "123456",
                email = "bill@withers.com",
                endereco = "Rua Street, 01",
                bairro = "Perdizes",
                cidade = "São Paulo",
                estado = "SP",
                cep = "05017000"
            ),
            Cliente(
                nome = "Stevie Wonder",
                telefone = "123456",
                email = "stevie@wonder.com",
                endereco = "Rua Street, 01",
                bairro = "Perdizes",
                cidade = "São Paulo",
                estado = "SP",
                cep = "05017000"
            ),
        )

        viewModelScope.launch(Dispatchers.Main) {
            repository.prePreenche(listaPrePreenche)
        }
    }


}

