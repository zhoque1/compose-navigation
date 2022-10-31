package com.example.mycomposeapplication.domain.models

data class ImageItems (
    val imageItems: List<ImageItem>
)

data class ImageItem(
    val title: String?,
    val url: String?,
    val thumbnailUrl: String?
)