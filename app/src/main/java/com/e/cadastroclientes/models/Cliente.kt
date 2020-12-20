package com.e.cadastroclientes.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Cliente(

    //id vai ser a primary key e vai incrementar automaticamente
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "nomeCliente")
    var nome: String,

    @ColumnInfo(name = "telefoneCliente")
    var telefone: Int,

    @ColumnInfo(name = "emailCliente")
    var email: String,

    @ColumnInfo(name = "endRuaCliente")
    var endereco: String,

    @ColumnInfo(name = "endBairroCliente")
    var bairro: String,

    @ColumnInfo(name = "endCidadeCliente")
    var cidade: String,

    @ColumnInfo(name = "endEstadoCliente")
    var estado: String,

    @ColumnInfo(name = "cepCliente")
    var cep: Long,

    ) : Serializable