package com.example.mycomposeapplication.domain.repositories

import com.example.mycomposeapplication.domain.models.ImageItems
import com.example.mycomposeapplication.domain.utils.ResultWrapper

interface ImageRepository {
    suspend fun getImageItems(album: Int): ResultWrapper<ImageItems>
}