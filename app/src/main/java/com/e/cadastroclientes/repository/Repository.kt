package com.e.cadastroclientes.repository

import com.e.cadastroclientes.dao.ClienteDao
import com.e.cadastroclientes.models.Cliente


interface Repository {

    suspend fun getAllClientesTask(): List<Cliente>

    suspend fun addClienteTask(cliente: Cliente)

    suspend fun atualizaClienteTask(cliente: Cliente)

    suspend fun deleteClienteTask(cliente: Cliente)

}

class RepositoryImpl(val clienteDao: ClienteDao) : Repository {
    override suspend fun getAllClientesTask() = clienteDao.getAllClientes()

    override suspend fun addClienteTask(cliente: Cliente) {
        clienteDao.addCliente(cliente)
    }

    override suspend fun atualizaClienteTask(cliente: Cliente) {
        clienteDao.updateCliente(cliente)
    }

    override suspend fun deleteClienteTask(cliente: Cliente) {
        clienteDao.deleteCliente(cliente)
    }


}