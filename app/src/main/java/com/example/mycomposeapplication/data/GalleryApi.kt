package com.example.mycomposeapplication.data


import com.example.mycomposeapplication.data.network.models.ApiGalleryItem
import com.example.mycomposeapplication.data.network.models.ApiImageItem
import com.example.mycomposeapplication.data.network.models.ApiImageItems
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GalleryApi {


    @GET("v2/list")
    suspend fun getGalleryWithPagination(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<List<ApiGalleryItem>>

    @GET("https://jsonplaceholder.typicode.com/albums/{id}/photos/")
    suspend fun getImages(@Path("id") id: Int): Response<ApiImageItems>

    @GET("https://picsum.photos/200/300?grayscale")
    suspend fun getRandomGrayScaleImage(): Response<ApiGalleryItem>

    @GET("id/{id}/info")
    suspend fun getGalleryItemDetails( @Path("id") id:Int): Response<ApiGalleryItem>
}