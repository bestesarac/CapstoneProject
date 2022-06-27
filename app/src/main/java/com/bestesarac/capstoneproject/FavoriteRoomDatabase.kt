package com.bestesarac.capstoneproject

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ProductsModel::class], version = 1)
abstract class FavoriteRoomDatabase : RoomDatabase() {

    abstract fun favoriteDAOInterface(): FavoriteDAOInterface

    companion object{

        @Volatile
        private var instance:FavoriteRoomDatabase?=null
        private val lock=Any()

        operator fun invoke(context:Context)= instance ?: synchronized(lock){
            instance ?: makeDatabase(context).also { instance=it }
        }

        private fun makeDatabase(context:Context)= Room.databaseBuilder(
            context.applicationContext,FavoriteRoomDatabase::class.java,"productdatabase"
        ).build()
    }
}