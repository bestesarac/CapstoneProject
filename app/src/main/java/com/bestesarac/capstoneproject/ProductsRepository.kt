package com.bestesarac.capstoneproject

import android.content.Context
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

class ProductsRepository(context: Context) {
    private val productsAPIService=ProductsAPIService()
    var productslist=MutableLiveData<List<ProductsModel>>()
    var discounteditemsList=MutableLiveData<List<ProductsModel>>()
    var crudResponse=MutableLiveData<CrudResponse>()
    var productById= MutableLiveData<ProductsModel>()
    var job: Job? =null
    var favoriteList= MutableLiveData<List<ProductsModel>>()
    var _productsFromBag= MutableLiveData<List<ProductsModel>>()
    private val favoriteDAOInterface: FavoriteDAOInterface?=FavoriteRoomDatabase(context).favoriteDAOInterface()

    fun getAllFavorites() {
        job= CoroutineScope(Dispatchers.IO).launch {
            val list=favoriteDAOInterface?.getFavorites()
            withContext(Dispatchers.Main){
                favoriteList.value=list.orEmpty()
            }
        }

    }
     fun addFavorite(productModel: ProductsModel){
        job= CoroutineScope(Dispatchers.IO).launch{
            favoriteDAOInterface?.addFavorite(productModel)
        }

    }
    fun getProductByIdFromRoom(id:Int){
        job= CoroutineScope(Dispatchers.IO).launch {
            val productbyid=favoriteDAOInterface?.getFavoriteById(id)
            withContext(Dispatchers.Main ){
                productbyid?.let {
                    productById.value=it
                }
            }
        }
    }

    fun deleteProductFromRoomDb(product: ProductsModel) {
        job = CoroutineScope(Dispatchers.IO).launch {
            favoriteDAOInterface?.deleteFavoriteById(product)
            val all_products=favoriteDAOInterface?.getFavorites()
            withContext(Dispatchers.Main ){
                if(all_products!=null){
                    favoriteList.value=all_products
                }
            }
        }
    }
    /*
     fun deleteFavorite(itemId: Int){
        job= CoroutineScope(Dispatchers.IO).launch {
            favoriteDAOInterface?.deleteFavoriteWithId(itemId)
        }
    }
     fun clearFavorite() {
        job= CoroutineScope(Dispatchers.IO).launch {
            favoriteDAOInterface?.clearFavorite()
        }

    }*/

    fun getProducts(){
        job= CoroutineScope(Dispatchers.IO).launch {
            val products=productsAPIService.getProducts()
            withContext(Dispatchers.Main){
                if (products.isSuccessful){
                    products.body()?.let {
                        productslist.value = it
                    }
                }
            }
        }
    }
    fun getDiscounteditems(){
        job= CoroutineScope(Dispatchers.IO).launch {
            val discounteditems=productsAPIService.getDiscounteditems()
            withContext(Dispatchers.Main){
                if(discounteditems.isSuccessful){
                    discounteditems.body()?.let{
                        discounteditemsList.value=it
                    }
                }
            }
        }
    }
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
        job= CoroutineScope(Dispatchers.IO).launch {
            val crudrespone=productsAPIService.addToBag(user,title,price,description,category,image,rate,count,sale_state)
            withContext(Dispatchers.Main){
                crudResponse.value=crudrespone
            }
        }
    }
    fun getBagProductsByUser(user:String){
        job= CoroutineScope(Dispatchers.IO).launch {
            val response=productsAPIService.getBagProductsByUser(user)
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    response.body()?.let {
                        _productsFromBag.value=it
                    }
                }
            }
        }
    }
    fun deleteProductFromBasket(id:Int){
        job= CoroutineScope(Dispatchers.IO).launch {
            val crudresponse=productsAPIService.deleteFromBag(id)
            withContext(Dispatchers.Main ){
                crudResponse.value=crudresponse
            }
        }
    }

}
