package com.example.mycomposeapplication.data.di

import com.example.mycomposeapplication.data.usecase.FetchGalleryItemDetailsUseCaseImpl
import com.example.mycomposeapplication.data.usecase.FetchImageItemsUseCaseImpl
import com.example.mycomposeapplication.data.usecase.FetchRandomGrayScaleImageUseCaseImpl
import com.example.mycomposeapplication.data.usecase.LoadGalleryUseCaseImpl
import com.example.mycomposeapplication.domain.usecases.FetchGalleryItemDetailsUseCase
import com.example.mycomposeapplication.domain.usecases.FetchImageItemsUseCase
import com.example.mycomposeapplication.domain.usecases.FetchRandomGrayScaleImageUseCase
import com.example.mycomposeapplication.domain.usecases.LoadGalleryUseCase
import org.koin.dsl.module


val useCaseModule = module {
    single<LoadGalleryUseCase>{ LoadGalleryUseCaseImpl(get()) }
    single<FetchRandomGrayScaleImageUseCase> { FetchRandomGrayScaleImageUseCaseImpl(get()) }
    single<FetchGalleryItemDetailsUseCase> { FetchGalleryItemDetailsUseCaseImpl(get()) }
    single <FetchImageItemsUseCase>{ FetchImageItemsUseCaseImpl(get())  }
}