package com.example.mycomposeapplication.domain.usecases

import com.example.mycomposeapplication.domain.models.ImageItems
import com.example.mycomposeapplication.domain.utils.ResultWrapper

interface FetchImageItemsUseCase {
    suspend operator fun invoke(album: Int): ResultWrapper<ImageItems>
}