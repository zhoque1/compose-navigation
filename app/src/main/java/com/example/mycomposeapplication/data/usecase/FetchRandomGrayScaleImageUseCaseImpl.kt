package com.example.mycomposeapplication.data.usecase

import com.example.mycomposeapplication.domain.models.GalleryImage
import com.example.mycomposeapplication.domain.repositories.GalleryRepository
import com.example.mycomposeapplication.domain.usecases.FetchRandomGrayScaleImageUseCase
import com.example.mycomposeapplication.domain.utils.ResultWrapper


class FetchRandomGrayScaleImageUseCaseImpl(
    private val galleryRepository: GalleryRepository
): FetchRandomGrayScaleImageUseCase {
    override suspend fun invoke(): ResultWrapper<GalleryImage> {
        return galleryRepository.getRandomGrayScaleImage()
    }
}