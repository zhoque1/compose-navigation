package com.example.mycomposeapplication.data.usecase

import androidx.paging.PagingData
import com.example.mycomposeapplication.domain.models.GalleryImage
import com.example.mycomposeapplication.domain.repositories.GalleryRepository
import com.example.mycomposeapplication.domain.usecases.LoadGalleryUseCase
import kotlinx.coroutines.flow.Flow

class LoadGalleryUseCaseImpl(
    private val galleryRepository: GalleryRepository
): LoadGalleryUseCase {
    override fun invoke(): Flow<PagingData<GalleryImage>> {
        return galleryRepository.loadGalleryWithPagination()
    }
}