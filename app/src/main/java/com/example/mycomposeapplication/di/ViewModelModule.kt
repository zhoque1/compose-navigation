package com.example.mycomposeapplication.di

import com.example.mycomposeapplication.ui.screens.home.ExampleViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ExampleViewModel() }
}