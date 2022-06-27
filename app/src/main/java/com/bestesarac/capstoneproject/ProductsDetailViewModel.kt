package com.bestesarac.capstoneproject

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProductsDetailViewModel(context: Context):ViewModel() {
    var crudResponse=MutableLiveData<CrudResponse>()
    private var productsRepository = ProductsRepository(context)
    var productById= MutableLiveData<ProductsModel>()

    fun addToBag(user: String,
                 title: String,
                 price: Double,
                 description: String,
                 category: String,
                 image: String,
                 rate: Double,
                 count: Int,
                 sale_state:Int
    ){
        productsRepository.addToBag(user, title, price, description, category, image, rate, count, sale_state)
        crudResponse=productsRepository.crudResponse
    }
    fun addFavorite(productModel: ProductsModel){
        productsRepository.addFavorite(productModel)
    }

    fun getProductByIdFromRoom(id:Int) {
        productsRepository.getProductByIdFromRoom(id)
        productById=productsRepository.productById
    }
    fun deleteProductFromRoomDb(product: ProductsModel) {
        productsRepository.deleteProductFromRoomDb(product)
    }
}