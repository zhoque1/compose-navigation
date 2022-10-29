package com.example.mycomposeapplication.domain.usecases

import androidx.paging.PagingData
import com.example.mycomposeapplication.domain.models.GalleryImage
import com.example.mycomposeapplication.domain.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface FetchGalleryItemDetailsUseCase {
    suspend operator fun invoke(id:Int): ResultWrapper<GalleryImage>
}