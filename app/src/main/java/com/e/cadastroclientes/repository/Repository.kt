package com.e.cadastroclientes.repository

import androidx.room.Insert
import androidx.room.Query
import com.e.cadastroclientes.dao.ClienteDao
import com.e.cadastroclientes.models.Cliente


interface Repository {

    suspend fun getAllClientesTask(): List<Cliente>

    suspend fun addClienteTask(cliente: Cliente)

    suspend fun atualizaClienteTask(cliente: Cliente)

}

class RepositoryImpl(val clienteDao: ClienteDao) : Repository {
    override suspend fun getAllClientesTask() = clienteDao.getAllClientes()

    override suspend fun addClienteTask(cliente: Cliente) {
        clienteDao.addCliente(cliente)
    }

    override suspend fun atualizaClienteTask(cliente: Cliente) {
        clienteDao.atualizaCliente(cliente)
    }


}