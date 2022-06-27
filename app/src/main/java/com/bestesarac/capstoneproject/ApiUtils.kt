package com.bestesarac.capstoneproject

import com.bestesarac.capstoneproject.Constants.BASE_URL
import retrofit2.Retrofit

class ApiUtils {
    companion object {
        fun getFavoriteDAOInterface():FavoriteDAOInterface{
            return RetrofitClient.getClient(BASE_URL).create(FavoriteDAOInterface::class.java)
        }
    }
}