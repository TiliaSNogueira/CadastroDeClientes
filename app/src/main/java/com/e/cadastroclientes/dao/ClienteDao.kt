package com.e.cadastroclientes.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.e.cadastroclientes.models.Cliente

@Dao
interface ClienteDao {

   @Query("SELECT * FROM tabelaClientes")
    suspend fun getAllClientes(): List<Cliente>

    @Insert
    suspend fun addCliente(cliente: Cliente)



}
