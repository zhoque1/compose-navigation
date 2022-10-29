package com.example.mycomposeapplication.ui.screens.demo

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.mycomposeapplication.domain.models.GalleryImage
import com.example.mycomposeapplication.domain.usecases.LoadGalleryUseCase
import com.example.mycomposeapplication.domain.utils.ResultWrapper
import com.example.mycomposeapplication.ui.base.BaseViewModel
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

class GalleryViewModel(private val galleryUseCase: LoadGalleryUseCase) :
    BaseViewModel<GalleryContract.Event, GalleryContract.State, GalleryContract.Effect>(){

//    val gallery: Flow<PagingData<GalleryImage>> = galleryUseCase.invoke().cachedIn(viewModelScope)

    override fun createInitialState(): GalleryContract.State{
        return GalleryContract.State(GalleryContract.GalleryState.Idle)
    }

    override fun handleEvent(event: GalleryContract.Event) {
        when(event){
            is GalleryContract.Event.OnInit ->{
                fetchGalleryList()
            }
        }
    }

    private fun fetchGalleryList(){
        val galleryItems: Flow<PagingData<GalleryImage>> = galleryUseCase.invoke().cachedIn(viewModelScope)
        setState {
            copy(
                galleryState = GalleryContract.GalleryState.GalleryResponseSuccess(galleryItems)
            )
        }

//        viewModelScope.launch {
//            when(val result = withContext(Dispatchers.IO){
//                galleryUseCase.invoke().cachedIn(viewModelScope)
//            }){
//                is ResultWrapper.Success -> {
//                    setState {
//                        copy(
//                            galleryState = GalleryContract.GalleryState.GalleryResponseSuccess(result)
//                        )
//                    }
//                }
//                else -> {
//                    handleError(result)
//                }
//            }
//        }
    }

    private fun handleError(resultWrapper: Flow<PagingData<GalleryImage>>){

    }

    private fun handleError(resultWrapper: ResultWrapper<Any>) {
        when (resultWrapper) {
            is ResultWrapper.AuthError -> {
                setErrorState()
                Timber.d("API Auth Error")
            }
            is ResultWrapper.GenericError -> {
                setErrorState()
                Timber.d("API Generic Error %s", resultWrapper.exception?.localizedMessage)
            }
            is ResultWrapper.SuccessNoData -> {
                Timber.d("API Success no data")
            }
            is ResultWrapper.NetworkError -> {
                setErrorState()
                Timber.d("API Network error")
            }
            else -> {
                setErrorState()
                Timber.d("API Error")
            }
        }
    }

    private fun setErrorState() {
        setState {
            copy(galleryState = GalleryContract.GalleryState.GalleryResponseError)
        }
    }

}