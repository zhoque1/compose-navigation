package com.example.mycomposeapplication.data.di

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.mycomposeapplication.data.GalleryApi
import com.example.mycomposeapplication.data.network.mappers.ApiMapper
import com.example.mycomposeapplication.data.network.models.ApiGalleryItem
import com.example.mycomposeapplication.data.paging.GalleryDataSource
import com.example.mycomposeapplication.data.repository.GalleryRepositoryImpl
import com.example.mycomposeapplication.data.repository.ImageRepositoryImpl
import com.example.mycomposeapplication.data.repository.PAGE_SIZE
import com.example.mycomposeapplication.domain.models.GalleryImage
import com.example.mycomposeapplication.domain.repositories.GalleryRepository
import com.example.mycomposeapplication.domain.repositories.ImageRepository

import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    single { provideGalleryDataSource(get(),get(named("ApiGalleryItemMapper"))) }
    single { provideGalleryPager(get()) }
    single {
        GalleryRepositoryImpl(
            pager = get(),
            galleryApi = get(),
            galleryItemMapper = get(named("ApiGalleryItemMapper")),

        ) as GalleryRepository
    }
    single {
        ImageRepositoryImpl(
            apiService = get(),
            imageItemsMapper = get(named("ApiImageItemsMapper"))
        ) as ImageRepository
    }
}

fun provideGalleryDataSource(galleryApi: GalleryApi, galleryItemMapper: ApiMapper<ApiGalleryItem?,GalleryImage>):GalleryDataSource{

    return GalleryDataSource(
        galleryApi = galleryApi,
        apiGalleryItemMapper = galleryItemMapper
    )

}

fun provideGalleryPager(dataSource: GalleryDataSource):Pager<Int,GalleryImage>{
    return Pager(PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false)){
        dataSource
    }
}