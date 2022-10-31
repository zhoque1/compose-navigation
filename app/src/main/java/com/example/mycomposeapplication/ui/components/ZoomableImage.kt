package com.example.mycomposeapplication.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
//import coil.compose.AsyncImage
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.mycomposeapplication.R
import com.example.mycomposeapplication.ui.components.DefaultShimmer
import com.example.mycomposeapplication.ui.extensions.toDp
import com.example.mycomposeapplication.ui.extensions.toPx
import com.example.mycomposeapplication.ui.theme.Black
import com.example.mycomposeapplication.ui.theme.White
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.launch
import timber.log.Timber

@ExperimentalFoundationApi
@Composable
fun ZoomableImage(
    imageUrl: String,
    scrollState: ScrollableState? = null,
    isRotation: Boolean = false,
    isZoomable: Boolean = true,
    width:Long? = null,
    height:Long? = null

) {

    val coroutineScope = rememberCoroutineScope()
    val maxScale = 5f
    val minScale = 1f
    val scale = remember { mutableStateOf(1f) }
    val rotationState = remember { mutableStateOf(1f) }
    val offsetX = remember { mutableStateOf(1f) }
    val offsetY = remember { mutableStateOf(1f) }
    val shipZIndex = remember { mutableStateOf(0f) }

    val defaultHeight = 250.dp.value.toPx
    var aspectRatio =1.5f
    if(width != null && width > 0  && height != null && height > 0 ){
        aspectRatio = width/height.toFloat()
    }
    val newWidth = LocalConfiguration.current.screenWidthDp.toPx
    val newHeight = newWidth / aspectRatio

    Timber.d("test : image = $imageUrl")
    Timber.d("width $width and height $height and newWidth $newWidth and newHeight $newHeight")

    Box(
        modifier = Modifier
            .zIndex(shipZIndex.value)
            .pointerInput(Unit) {
                if (isZoomable) {
                    forEachGesture {
                        awaitPointerEventScope {
                            awaitFirstDown()
                            do {
                                val event = awaitPointerEvent()
                                scale.value *= event.calculateZoom()
                                if (scale.value > 1) {
                                    scrollState?.run {
                                        coroutineScope.launch {
                                            setScrolling(false)
                                        }
                                    }
                                    val offset = event.calculatePan()
                                    offsetX.value += offset.x
                                    offsetY.value += offset.y
                                    rotationState.value += event.calculateRotation()
                                    scrollState?.run {
                                        coroutineScope.launch {
                                            setScrolling(true)
                                        }
                                    }
                                    shipZIndex.value = 1f
                                } else {
                                    shipZIndex.value = 0f
                                    scale.value = 1f
                                    offsetX.value = 1f
                                    offsetY.value = 1f

                                }
                            } while (event.changes.any { it.pressed })
                            shipZIndex.value = 0f
                            scale.value = 1f
                            offsetX.value = 1f
                            offsetY.value = 1f
                        }

                    }
                }
            }

    ) {

        GlideImage(
            imageModel = imageUrl,
            contentDescription = "",
            modifier = Modifier
                .zIndex(shipZIndex.value)
                .fillMaxWidth()
                .height(newHeight.toDp.toInt().dp)
                .align(Alignment.Center)
                .graphicsLayer {
                    if (isZoomable) {
                        scaleX = maxOf(minScale, minOf(maxScale, scale.value))
                        scaleY = maxOf(minScale, minOf(maxScale, scale.value))
                        if (isRotation) {
                            rotationZ = rotationState.value
                        }
                        translationX = offsetX.value
                        translationY = offsetY.value
                    }
                },
            requestOptions = {
                RequestOptions()
//                    .override(newWidth.toInt(),newHeight.toInt())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
            },
            contentScale = ContentScale.Crop,
            // shows a progress indicator when loading an image.
            loading = {
                DefaultShimmer()
            },
            // shows an error text message when request failed.
            failure = {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center ) {
                    Image(
                        painter = painterResource(id = R.drawable.no_image_available),
                        contentDescription = "No Image Available",
                        modifier = Modifier.height(250.dp).fillMaxWidth().align(Alignment.Center),
                    )
                    Text(
                        text = "No Image Available",
                        modifier = Modifier.align(Alignment.Center),
                        color = White
                    )
                }
            },
        )
    }
}

suspend fun ScrollableState.setScrolling(value: Boolean) {
    scroll(scrollPriority = MutatePriority.PreventUserInput) {
        when (value) {
            true -> Unit
            else -> awaitCancellation()
        }
    }
}