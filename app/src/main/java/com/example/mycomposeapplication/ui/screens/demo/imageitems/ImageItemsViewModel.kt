package com.example.mycomposeapplication.ui.screens.demo.imageitems

import androidx.lifecycle.viewModelScope
import com.example.mycomposeapplication.domain.usecases.FetchImageItemsUseCase
import com.example.mycomposeapplication.domain.utils.ResultWrapper
import com.example.mycomposeapplication.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class ImageItemsViewModel(private val imageItemsUseCase: FetchImageItemsUseCase)
    :BaseViewModel<ImageItemsContract.Event, ImageItemsContract.State, ImageItemsContract.Effect>()
{
    override fun createInitialState(): ImageItemsContract.State {
        return ImageItemsContract.State(ImageItemsContract.ImageItemsState.Idle)
    }

    override fun handleEvent(event: ImageItemsContract.Event) {
        when(event){
            is ImageItemsContract.Event.OnInit ->{
//                fetchImageItems(event.album)
            }
            else ->{}
        }
    }

    private fun fetchImageItems(album: Int) {
        viewModelScope.launch {
            when (val result = withContext(Dispatchers.IO) {
                imageItemsUseCase.invoke(album = album%100)
            }) {

                is ResultWrapper.Success -> {
                    Timber.d("API Success")
                    val agentDetail = result.data
                    setState {
                        copy(
                            imageItemsState =
                            ImageItemsContract.ImageItemsState.ImageItemsResponseSuccess(
                                agentDetail
                            )
                        )
                    }
                }
                else -> {
                    handleError(result)
                }
            }
        }
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
            copy(imageItemsState = ImageItemsContract.ImageItemsState.ImageItemsResponseError)
        }
    }
}