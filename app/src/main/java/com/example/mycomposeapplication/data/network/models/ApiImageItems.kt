package com.example.mycomposeapplication.data.network.models

import com.google.gson.annotations.SerializedName


data class ApiImageItems(
    val imageItems: ArrayList<ApiImageItem>
)

data class ApiImageItem(
    @SerializedName("title"        ) var title        : String? = null,
    @SerializedName("url"          ) var url          : String? = null,
    @SerializedName("thumbnailUrl" ) var thumbnailUrl : String? = null
)
