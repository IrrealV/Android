package com.example.appciudades.dominio.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appciudades.dominio.models.Cerveza

@Database(entities = [Cerveza::class], version = 1)
abstract class MyBD : RoomDatabase() {
    abstract fun BeerDao(): BeerDao
    companion object {
        const val DBNAME = "contactos_database"

        @Volatile
        private var INSTANCE: MyBD? = null

        fun getDatabase(context: Context): MyBD {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(MyBD::class) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyBD::class.java,
                    DBNAME
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}