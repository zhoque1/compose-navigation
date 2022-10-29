package com.example.mycomposeapplication.data


import com.example.mycomposeapplication.data.network.models.ApiGalleryItem
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

    @GET("https://picsum.photos/200/300?grayscale")
    suspend fun getRandomGrayScaleImage(): Response<ApiGalleryItem>

    @GET("id/{id}/info")
    suspend fun getGalleryItemDetails( @Path("id") id:Int): Response<ApiGalleryItem>
}