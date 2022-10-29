package com.example.mycomposeapplication.data.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import com.example.mycomposeapplication.data.GalleryApi
import com.example.mycomposeapplication.data.network.getErrorResultWrapper
import com.example.mycomposeapplication.data.network.interceptors.NetworkUnavailableException
import com.example.mycomposeapplication.data.network.mappers.ApiMapper
import com.example.mycomposeapplication.data.network.models.ApiGalleryItem
import com.example.mycomposeapplication.domain.models.GalleryImage
import com.example.mycomposeapplication.domain.repositories.GalleryRepository
import com.example.mycomposeapplication.domain.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

const val PAGE_SIZE = 10

class GalleryRepositoryImpl(
    private val pager: Pager<Int, GalleryImage>,
    private val galleryApi: GalleryApi,
    private val galleryItemMapper: ApiMapper<ApiGalleryItem?, GalleryImage>
) : GalleryRepository {


    override fun loadGalleryWithPagination(): Flow<PagingData<GalleryImage>> {
        return pager.flow
    }

    override suspend fun getRandomGrayScaleImage(): ResultWrapper<GalleryImage> {

        return try {
            val response = galleryApi.getRandomGrayScaleImage()

            if (response.isSuccessful) {
                val body = response.body()
                body?.run {
                    ResultWrapper.Success(galleryItemMapper.mapToDomain(this))
                } ?: run {
                    ResultWrapper.Success(null)
                }
            } else {
                response.getErrorResultWrapper()
            }

        } catch (ex: NetworkUnavailableException) {
            ResultWrapper.NetworkError
        } catch (ex: Exception) {
            ResultWrapper.GenericError(Exception("Api Error"))
        }


    }

    override suspend fun getGalleryItemDetails(id: Int): ResultWrapper<GalleryImage> {
        return try {
            val response = galleryApi.getGalleryItemDetails(id)

            if (response.isSuccessful) {
                val body = response.body()
                body?.run {
                    ResultWrapper.Success(galleryItemMapper.mapToDomain(this))
                } ?: run {
                    ResultWrapper.Success(null)
                }
            } else {
                response.getErrorResultWrapper()
            }

        } catch (ex: NetworkUnavailableException) {
            ResultWrapper.NetworkError
        } catch (ex: Exception) {
            ResultWrapper.GenericError(Exception("Api Error"))
        }

    }

}