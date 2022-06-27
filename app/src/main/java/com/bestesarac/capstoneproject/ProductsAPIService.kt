package com.bestesarac.capstoneproject

import com.bestesarac.capstoneproject.Constants.BASE_URL
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductsAPIService {
    private val productsAPI = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(ProductsAPI::class.java,)
    private val discountedAPI = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(ProductsAPI::class.java,)

    suspend fun getProducts(): Response<List<ProductsModel>> {
        return productsAPI.getProducts()
    }
    suspend fun getDiscounteditems(): Response<List<ProductsModel>>{
        return discountedAPI.getDiscounteditems()
    }
    suspend fun addToBag(user: String, title: String, price: Double, description: String, category: String, image: String, rate: Double, count: Int, sale_state: Int): CrudResponse {
        return productsAPI.addToBag(user,title,price,description,category,image,rate,count,sale_state)
    }
    suspend fun getBagProductsByUser(user:String):Response<List<ProductsModel>>{
        return productsAPI.getBagProductsByUser(user)
    }
    suspend fun deleteFromBag(id:Int):CrudResponse{
        return productsAPI.deleteFromBag(id)
    }

}
