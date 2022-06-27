package com.bestesarac.capstoneproject

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
@Dao
interface FavoriteDAOInterface {
    @Insert
    suspend fun addFavorite(productsModel: ProductsModel)
    @Query("SELECT * FROM ProductsModel")
    suspend fun getFavorites(): List<ProductsModel>?

    @Delete
    suspend fun deleteFavoriteById(productsModel:ProductsModel)

    @Query("SELECT * FROM ProductsModel WHERE id=:id")
    suspend fun getFavoriteById(id:Int):ProductsModel
}