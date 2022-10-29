package com.example.mycomposeapplication.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mycomposeapplication.data.GalleryApi
import com.example.mycomposeapplication.data.network.ApiException
import com.example.mycomposeapplication.data.network.mappers.ApiMapper
import com.example.mycomposeapplication.data.network.models.ApiGalleryItem
import com.example.mycomposeapplication.domain.models.GalleryImage
import retrofit2.HttpException
import java.io.IOException

class GalleryDataSource constructor(
    private val galleryApi: GalleryApi,
    private val apiGalleryItemMapper: ApiMapper<ApiGalleryItem?, GalleryImage>
) : PagingSource<Int, GalleryImage>() {
    override fun getRefreshKey(state: PagingState<Int, GalleryImage>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GalleryImage> {
        return try {

            val nextPage = params.key ?: 1
            val galleryResponse = galleryApi.getGalleryWithPagination(
                page = nextPage,
                limit = 10
            )
            val gallery = galleryResponse.body()
            gallery?.run {

                val nextKey = if (this.isEmpty()) null else nextPage.plus(1)
                val prevKey = if (nextPage == 1) null else nextPage - 1

                return if (params.key == null && gallery.isEmpty()) {
                    LoadResult.Error(ApiException("Gallery Fetch Error"))
                } else {
                    LoadResult.Page(
                        data = this.map { apiGalleryItemMapper.mapToDomain(it) },
                        prevKey = prevKey,
                        nextKey = nextKey
                    )
                }
            } ?: run {
                throw ApiException("Gallery Fetch Error")
            }


        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}