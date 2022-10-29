package com.example.mycomposeapplication.data.network.models

import com.google.gson.annotations.SerializedName

data class GalleryLoadRequest(
    val page:Int,
    val limit:Int = 10
)

data class ApiGalleryItem(
    val id: Int,
    val author: String?,
    val width: Int,
    val height: Int,
    val url: String,
    @SerializedName("download_url") val downloadUrl: String
)
