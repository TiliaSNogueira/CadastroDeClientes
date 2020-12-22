package com.e.cadastroclientes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.e.cadastroclientes.dao.ClienteDao
import com.e.cadastroclientes.models.Cliente

@Database(entities = [Cliente::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun clienteDao(): ClienteDao

    companion object {
        @Volatile
        private var instance: AppDataBase? = null
        private val LOCK = Any()

        //passa o contexto e pega a instancia do banco - uma instancia unica para o app inteiro
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            AppDataBase::class.java, "cadastroclientes.db"
        ).build()
    }


}