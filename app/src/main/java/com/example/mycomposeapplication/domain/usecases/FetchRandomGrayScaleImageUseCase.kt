package com.example.mycomposeapplication.domain.usecases

import androidx.paging.PagingData
import com.example.mycomposeapplication.domain.models.GalleryImage
import com.example.mycomposeapplication.domain.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface FetchRandomGrayScaleImageUseCase {
    suspend operator fun invoke(): ResultWrapper<GalleryImage>
}