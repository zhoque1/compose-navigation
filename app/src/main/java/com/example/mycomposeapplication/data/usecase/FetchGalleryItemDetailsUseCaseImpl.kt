package com.example.mycomposeapplication.data.usecase

import com.example.mycomposeapplication.domain.models.GalleryImage
import com.example.mycomposeapplication.domain.repositories.GalleryRepository
import com.example.mycomposeapplication.domain.usecases.FetchGalleryItemDetailsUseCase
import com.example.mycomposeapplication.domain.utils.ResultWrapper

class FetchGalleryItemDetailsUseCaseImpl(
    private val galleryRepository: GalleryRepository,
): FetchGalleryItemDetailsUseCase {
    override suspend fun invoke(id: Int): ResultWrapper<GalleryImage> {
        return galleryRepository.getGalleryItemDetails(id)
    }
}