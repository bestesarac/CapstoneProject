package com.bestesarac.capstoneproject

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class BagViewModel(context: Context):ViewModel() {
    private var productsRepository = ProductsRepository(context)
    var _productsFromBag = MutableLiveData<List<ProductsModel>>()
    var crudResponse=MutableLiveData<CrudResponse>()


    fun getBagProductsByUser(user: String) {
        productsRepository.getBagProductsByUser(user)
        _productsFromBag = productsRepository._productsFromBag
    }

    fun deleteProductFromBasket(id: Int) {
        productsRepository.deleteProductFromBasket(id)
        crudResponse=productsRepository.crudResponse
    }
}
