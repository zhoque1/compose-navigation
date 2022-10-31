package com.example.mycomposeapplication.data.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.mycomposeapplication.data.GalleryApi
import com.example.mycomposeapplication.data.network.ConnectionManager
import com.example.mycomposeapplication.data.network.interceptors.HeaderInterceptor
import com.example.mycomposeapplication.data.network.interceptors.NetworkStatusInterceptor
import com.example.mycomposeapplication.data.network.interceptors.SimpleAuthInterceptor
import com.example.mycomposeapplication.data.network.mappers.ApiGalleryItemMapper
import com.example.mycomposeapplication.data.network.mappers.ApiImageItemsMapper
import com.example.mycomposeapplication.data.network.mappers.ApiMapper
import com.example.mycomposeapplication.data.network.models.ApiGalleryItem
import com.example.mycomposeapplication.data.network.models.ApiImageItems
import com.example.mycomposeapplication.domain.models.GalleryImage
import com.example.mycomposeapplication.domain.models.ImageItems


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {

    single { ConnectionManager(androidContext()) }
    single { NetworkStatusInterceptor(get()) }
    single { SimpleAuthInterceptor(get()) }
    single { HeaderInterceptor() }
    single { provideOkHttpLoggingInterceptor() }
    single { provideOkHttpClient(get(), get(), get(), get()) }
    single { provideRetrofit(get()) }
    single { provideGalleryApi(get()) }
    single { provideSharedPrefs(androidApplication())}

    //mapper objects
    single(named("ApiGalleryItemMapper")){ ApiGalleryItemMapper() as ApiMapper<ApiGalleryItem?,GalleryImage> }
    single(named("ApiImageItemsMapper")) { ApiImageItemsMapper() as ApiMapper<ApiImageItems?, ImageItems?>}
}

fun provideSharedPrefs(androidApplication: Application):SharedPreferences {
    return androidApplication.getSharedPreferences(
        "demo_shared_prefs",
        Context.MODE_PRIVATE
    )
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BuildConfig.BASE_API_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideOkHttpClient(
    authInterceptor:SimpleAuthInterceptor,
    loggingInterceptor: HttpLoggingInterceptor,
    networkStatusInterceptor: NetworkStatusInterceptor,
    headerInterceptor: HeaderInterceptor
): OkHttpClient {
    val client = OkHttpClient().newBuilder()

    client.addInterceptor(networkStatusInterceptor)
    client.addInterceptor(authInterceptor)
    client.addInterceptor(headerInterceptor)
    client.addInterceptor(loggingInterceptor)

    //client.addNetworkInterceptor(StethoInterceptor())


    return client.build()
}

fun provideOkHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    return logging
}

fun provideGalleryApi(retrofit: Retrofit): GalleryApi = retrofit.create(GalleryApi::class.java)
