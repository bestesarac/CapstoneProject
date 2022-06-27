package com.bestesarac.capstoneproject

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel(context: Context) :ViewModel() {
    var _productslist= MutableLiveData<List<ProductsModel>>()
    var _discounteditemslist= MutableLiveData<List<ProductsModel>>()
    val productsRepository=ProductsRepository(context)

    var _isItemAddedFavorite = MutableLiveData<Boolean>()
    val isItemAddedFavorite: LiveData<Boolean>
        get() = _isItemAddedFavorite

    init {
        getProducts()
        getDiscounteditems()
    }

    fun getProducts(){
        productsRepository.getProducts()
        _productslist=productsRepository.productslist
    }
    fun getDiscounteditems(){
        productsRepository.getDiscounteditems()
        _discounteditemslist=productsRepository.discounteditemsList
    }
    /*fun addFavorite(productsModel: ProductsModel) {
        productsRepository.addFavorite(productsModel)

    }*/
}