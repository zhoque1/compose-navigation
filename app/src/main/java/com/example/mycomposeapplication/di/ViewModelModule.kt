package com.example.mycomposeapplication.di

import com.example.mycomposeapplication.ui.GlobalViewModel
import com.example.mycomposeapplication.ui.screens.home.HomeDetailViewModel
import com.example.mycomposeapplication.ui.screens.demo.GalleryViewModel
import com.example.mycomposeapplication.ui.screens.demo.imageitems.ImageItemsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { GlobalViewModel() }
    viewModel { HomeDetailViewModel() }
    viewModel { GalleryViewModel(get()) }
    viewModel { ImageItemsViewModel(get())}
}