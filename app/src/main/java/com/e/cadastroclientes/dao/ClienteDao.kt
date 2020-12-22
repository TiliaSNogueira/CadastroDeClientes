package com.e.cadastroclientes.dao


import androidx.room.*
import com.e.cadastroclientes.models.Cliente

@Dao
interface ClienteDao {

    //pega todos clientes da tabela ordenados por ordem alfabética
    @Query("SELECT * FROM tabelaClientes ORDER BY nomeCliente ASC")
    suspend fun getAllClientes(): List<Cliente>

    //quando já tiver um cliente com os mesmos dados vai salvar mesmo assim
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCliente(cliente: Cliente)

    @Update
    suspend fun updateCliente(cliente: Cliente)

    @Delete
    suspend fun deleteCliente(cliente: Cliente)


}
