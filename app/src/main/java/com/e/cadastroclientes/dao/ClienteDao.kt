package com.e.cadastroclientes.dao


import androidx.room.*
import com.e.cadastroclientes.models.Cliente

@Dao
interface ClienteDao {

    @Query("SELECT * FROM tabelaClientes ORDER BY nomeCliente ASC")
    suspend fun getAllClientes(): List<Cliente>

    //quer dizer que quando já tiver um user com os mesmos dados vamos ignorar e vai salvar tb
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCliente(cliente: Cliente)

    @Update
    suspend fun atualizaCliente(cliente: Cliente)

    @Delete
    suspend fun deleteCliente(cliente: Cliente)


}
