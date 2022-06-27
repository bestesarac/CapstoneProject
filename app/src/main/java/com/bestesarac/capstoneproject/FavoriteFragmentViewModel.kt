package com.bestesarac.capstoneproject

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FavoriteFragmentViewModel(context:Context): ViewModel() {
    private val productsRepository=ProductsRepository(context)

    var favoriteList = MutableLiveData<List<ProductsModel>>()

    fun getAllFavorites() {
        productsRepository.getAllFavorites()
        favoriteList=productsRepository.favoriteList
    }
    fun deleteProductFromRoomDb(product: ProductsModel) {
        productsRepository.deleteProductFromRoomDb(product)
    }
}