package com.example.mycomposeapplication.domain.usecases

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.mycomposeapplication.domain.models.GalleryImage
import com.example.mycomposeapplication.domain.repositories.GalleryRepository
import kotlinx.coroutines.flow.Flow

interface LoadGalleryUseCase {
    operator fun invoke(): Flow<PagingData<GalleryImage>>
}