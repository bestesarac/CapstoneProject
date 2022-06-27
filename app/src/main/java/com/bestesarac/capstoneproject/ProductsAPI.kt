package com.bestesarac.capstoneproject

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductsAPI {
    @GET("api/ecommerce/get_products.php")
    suspend fun getProducts(): Response<List<ProductsModel>>

    @GET("api/ecommerce/get_sale_products.php")
    suspend fun getDiscounteditems(): Response<List<ProductsModel>>

    @POST("api/ecommerce/add_to_bag.php")
    @FormUrlEncoded
    suspend fun addToBag(
        @Field("user") user:String,
        @Field("title") title:String,
        @Field("price") price:Double,
        @Field("description") description:String,
        @Field("category") category:String,
        @Field("image") image:String,
        @Field("rate") rate:Double,
        @Field("count") count:Int,
        @Field("sale_state") sale_state:Int,
    ):CrudResponse

    @POST("api/ecommerce/get_bag_products_by_user.php")
    @FormUrlEncoded
    suspend fun getBagProductsByUser(
        @Field("user") user:String
    ):Response<List<ProductsModel>>

    @POST("api/ecommerce/delete_from_bag.php")
    @FormUrlEncoded
    suspend fun deleteFromBag(
        @Field("id") id:Int
    ):CrudResponse

}
