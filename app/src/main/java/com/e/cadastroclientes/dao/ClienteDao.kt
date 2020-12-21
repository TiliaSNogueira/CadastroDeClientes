package com.e.cadastroclientes.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.e.cadastroclientes.models.Cliente

@Dao
interface ClienteDao {

   @Query("SELECT * FROM tabelaClientes")
    suspend fun getAllClientes(): List<Cliente>

    //quer dizer que quando já tiver um user com os mesmos dados vamos ignorar e vai salvar tb
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCliente(cliente: Cliente)



}
