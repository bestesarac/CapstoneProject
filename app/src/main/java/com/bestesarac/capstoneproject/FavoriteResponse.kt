package com.bestesarac.capstoneproject

import com.google.gson.annotations.SerializedName

data class FavoriteResponse (
    @SerializedName("items") var items: List<ProductsModel>,
    @SerializedName("success") var success: Int)