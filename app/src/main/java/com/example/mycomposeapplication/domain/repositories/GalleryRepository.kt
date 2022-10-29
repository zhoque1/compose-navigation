package com.example.mycomposeapplication.domain.repositories

import androidx.paging.PagingData
import com.example.mycomposeapplication.domain.models.GalleryImage
import com.example.mycomposeapplication.domain.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface GalleryRepository {
    fun loadGalleryWithPagination(): Flow<PagingData<GalleryImage>>

    //suspend fun getBlurGalleryImage(blurLevel:Int = 1): ResultWrapper<GalleryImage>
    suspend fun getRandomGrayScaleImage(): ResultWrapper<GalleryImage>

    suspend fun getGalleryItemDetails(id:Int): ResultWrapper<GalleryImage>


    //likeGalleryImage
    //unlikeGalleryImage
    //Download Image

}