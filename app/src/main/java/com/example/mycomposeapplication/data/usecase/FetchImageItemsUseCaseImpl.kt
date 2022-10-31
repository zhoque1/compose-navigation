package com.example.mycomposeapplication.data.usecase

import com.example.mycomposeapplication.domain.models.ImageItems
import com.example.mycomposeapplication.domain.repositories.ImageRepository
import com.example.mycomposeapplication.domain.usecases.FetchImageItemsUseCase
import com.example.mycomposeapplication.domain.utils.ResultWrapper

class FetchImageItemsUseCaseImpl(
    private val imageRepository: ImageRepository
): FetchImageItemsUseCase {
    override suspend fun invoke(album: Int): ResultWrapper<ImageItems> {
        return imageRepository.getImageItems(album)
    }
}