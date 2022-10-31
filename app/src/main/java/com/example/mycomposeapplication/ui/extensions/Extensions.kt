package com.example.mycomposeapplication.ui.extensions

import android.content.res.Resources
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber


/**
 * Returns a [StateFlow] that access data associated with the given key.
 *
 * @param scope The scope used to synchronize the [StateFlow] and [SavedStateHandle]
 * @param key The identifier for the value
 * @param initialValue If no value exists with the given [key], a new one is created
 *  with the given [initialValue].
 *
 * @see SavedStateHandle.getLiveData
 */
fun <T> SavedStateHandle.getStateFlow(
    key: String,
    scope: CoroutineScope,
    initialValue: T? = get(key)
): MutableStateFlow<T?> = this.let { handle ->
    val liveData = handle.getLiveData<T?>(key, initialValue).also { liveData ->
        if (liveData.value === initialValue) {
            liveData.value = initialValue
        }
    }
    val mutableStateFlow = MutableStateFlow(liveData.value)

    val observer: Observer<T?> = Observer { value ->
        if (value != mutableStateFlow.value) {
            mutableStateFlow.value = value
        }
    }
    liveData.observeForever(observer)

    scope.launch {
        mutableStateFlow.also { flow ->
            flow.onCompletion {
                withContext(Dispatchers.Main.immediate) {
                    Timber.d("flow complete")
                    liveData.removeObserver(observer)
                }
            }.collect { value ->
                withContext(Dispatchers.Main.immediate) {
                    if (liveData.value != value) {
                        liveData.value = value
                    }
                }
            }
        }
    }
    mutableStateFlow
}
//
///**
// * @desc - to save the text field state
// */
//fun SavedStateHandle.saveTextFieldValue(
//    key: String,
//    stateFlow: MutableStateFlow<InputWrapper?>,
//    input: String,
//) = this.let { handle ->
//    handle[key] = stateFlow.value?.copy(value = input)
//}
//
///**
// * @desc set up the error code for the text field
// */
//fun SavedStateHandle.setTextFieldError(
//    key: String,
//    stateFlow: MutableStateFlow<InputWrapper?>,
//    error: Int?,
//) = this.let { handle ->
//    handle[key] = stateFlow.value?.copy(errorId = error)
//}
//
///**
// * @desc - clear the error for textfield
// */
//fun SavedStateHandle.clearTextFieldError(
//    key: String,
//    stateFlow: MutableStateFlow<InputWrapper?>,
//) = this.let { handle ->
//    handle[key] = stateFlow.value?.copy(errorId = null)
//}


val Float.toPx get() = this * Resources.getSystem().displayMetrics.density

val Float.toDp get() = this / Resources.getSystem().displayMetrics.density

val Int.toPx get() = (this * Resources.getSystem().displayMetrics.density).toInt()

val Int.toDp get() = (this / Resources.getSystem().displayMetrics.density).toInt()

