package com.example.mycomposeapplication.data.network.mappers

import com.example.mycomposeapplication.data.network.models.ApiImageItem
import com.example.mycomposeapplication.data.network.models.ApiImageItems
import com.example.mycomposeapplication.domain.models.ImageItem
import com.example.mycomposeapplication.domain.models.ImageItems

class ApiImageItemsMapper: ApiMapper<ApiImageItems?, ImageItems?> {
    override fun mapToDomain(apiEntity: ApiImageItems?): ImageItems? {
        apiEntity?.let{
            return ImageItems(
                imageItems = imageItemMapper(it.imageItems)
            )
        }
        return null
    }

    private fun imageItemMapper(imageItems: ArrayList<ApiImageItem>): List<ImageItem> {
        val photos = ArrayList<ImageItem>()
        for(item in imageItems){
            item.apply {
                photos.add(
                    ImageItem(
                        title = title,
                        url = url,
                        thumbnailUrl = thumbnailUrl
                    )
                )
            }
        }
        return photos
    }
}