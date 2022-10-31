package com.example.mycomposeapplication.data.network.models


data class ApiImageItems(
    val imageItems: ArrayList<ApiImageItem>
)

data class ApiImageItem(
    val title: String?,
    val url: String?,
    val thumbnailUrl: String?
)
