package com.example.mycomposeapplication.data.repository

import com.example.mycomposeapplication.data.GalleryApi
import com.example.mycomposeapplication.data.network.getErrorResultWrapper
import com.example.mycomposeapplication.data.network.interceptors.NetworkUnavailableException
import com.example.mycomposeapplication.data.network.mappers.ApiImageItemsMapper
import com.example.mycomposeapplication.domain.models.ImageItems
import com.example.mycomposeapplication.domain.repositories.ImageRepository
import com.example.mycomposeapplication.domain.utils.ResultWrapper

class ImageRepositoryImpl(
    private val apiService: GalleryApi,
    private val imageItemsMapper: ApiImageItemsMapper
): ImageRepository {
    override suspend fun getImageItems(album: Int): ResultWrapper<ImageItems> {
        return try {
            val response = apiService.getImages(album)
            if (response.isSuccessful) {
                val body = response.body()
                body?.run {
                    ResultWrapper.Success(imageItemsMapper.mapToDomain(body))
                } ?: run {
                    ResultWrapper.SuccessNoData
                }
            } else {
                response.getErrorResultWrapper()
            }
        } catch (ex: NetworkUnavailableException) {
            ResultWrapper.NetworkError
        } catch (ex: Exception) {
            ResultWrapper.GenericError(Exception(ex))
        }
    }
}