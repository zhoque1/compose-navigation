package com.example.mycomposeapplication.data.network.mappers


import com.example.mycomposeapplication.data.network.models.ApiGalleryItem
import com.example.mycomposeapplication.domain.models.GalleryImage

import kotlin.random.Random

class ApiGalleryItemMapper : ApiMapper<ApiGalleryItem?,GalleryImage> {
    override fun mapToDomain(apiEntity: ApiGalleryItem?): GalleryImage {

        return GalleryImage(
            id = apiEntity?.id ?: -1,
            author = apiEntity?.author ?: "",
            width = apiEntity?.width ?: -1,
            height = apiEntity?.height ?: -1,
            url = apiEntity?.url ?: "",
            downloadUrl = apiEntity?.downloadUrl ?: "",
            grayscaleUrl =  apiEntity?.downloadUrl?.plus("?grayscale") ?: "",
            //blur values acceptable 1..10
            blurUrl = apiEntity?.downloadUrl?.plus("?blur=${Random.nextInt(1, 11)}") ?: ""
        )

    }

}