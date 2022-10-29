package com.example.mycomposeapplication.ui.screens.demo

import android.content.Context
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.mycomposeapplication.domain.models.GalleryImage
import com.google.accompanist.coil.rememberCoilPainter
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.viewModel
import timber.log.Timber

//@Composable
//fun GalleryListFromActivity(modifier: Modifier = Modifier, viewModel: GalleryViewModel, context: Context
//) {
//    ShowGalleryList(modifier, galleryList = viewModel.gallery, context)
//}

@Composable
fun GalleryList(modifier: Modifier = Modifier, context: Context) {
    val galleryViewModel: GalleryViewModel by viewModel()

    LaunchedEffect(true) {
        galleryViewModel.setEvent(
            GalleryContract.Event.OnInit
        )
    }

    when(val state = galleryViewModel.uiState.value.galleryState){
        is GalleryContract.GalleryState.GalleryResponseSuccess ->{
            Timber.d("test : GalleryResponseSuccess")
            ShowGalleryList(modifier, galleryList = state.gallery, context)
        }
        else -> {}
    }
//    ShowGalleryList(modifier, galleryList = galleryViewModel.gallery, context)
}

@Composable
fun ShowGalleryList(
    modifier: Modifier,
    galleryList: Flow<PagingData<GalleryImage>>,
    context: Context
) {

    val galleryListItems: LazyPagingItems<GalleryImage> = galleryList.collectAsLazyPagingItems()


    LazyColumn {
        items(galleryListItems) { item ->
            item?.let {
                GalleryItem(
                    galleryItemData = it,
                    onClick = {
//                        onItemClicked(it.id)
                    })
            }
        }
        galleryListItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    //You can add modifier to manage load state when first time response page is loading
                    item { LoadingItem() }
                }
                loadState.append is LoadState.Loading -> {
                    //You can add modifier to manage load state when next response page is loading
                    item { LoadingItem() }
                }
                loadState.append is LoadState.Error -> {
                    //You can use modifier to show error message
                    item {
                        Box(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "Error Fetching Gallery",
                                color = Color.Black
                            )
                        }
                    }
                }
                loadState.refresh is LoadState.Error -> {
                    //You can use modifier to show error message
                    item {
                        Box(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "Error Fetching Gallery",
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LoadingItem() {
    CircularProgressIndicator(
        modifier =
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .wrapContentWidth(
                Alignment.CenterHorizontally
            )
    )
}

@Composable
fun GalleryItem(galleryItemData: GalleryImage, onClick: () -> Unit) {
    var toggleColor by rememberSaveable { mutableStateOf(false) }
    var expanded by rememberSaveable { mutableStateOf(false) }
    val haptic = LocalHapticFeedback.current

    Card(
        modifier = Modifier
            .padding(
                bottom = 5.dp, top = 5.dp,
                start = 5.dp, end = 5.dp
            )
            .fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        elevation = 12.dp
    ) {
        Column(
            modifier = Modifier.animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
        ) {
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(MaterialTheme.colors.surface)
            ) {
                Surface(
                    modifier = Modifier.size(130.dp),
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colors.surface.copy(
                        alpha = 0.2f
                    )
                ) {
                    if (toggleColor) {
                        val image = rememberCoilPainter(
                            request = galleryItemData.grayscaleUrl,
                            fadeIn = true
                        )
                        Image(
                            painter = image,
                            contentDescription = null,
                            modifier = Modifier
                                .height(100.dp)
                                .clip(shape = RoundedCornerShape(12.dp))
                                .clickable { onClick() },
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        val image = rememberCoilPainter(
                            request = galleryItemData.downloadUrl,
                            fadeIn = true
                        )
                        Image(
                            painter = image,
                            contentDescription = null,
                            modifier = Modifier
                                .height(100.dp)
                                .clip(shape = RoundedCornerShape(12.dp))
                                .clickable { onClick() },
                            contentScale = ContentScale.Crop
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .align(Alignment.CenterVertically)
                ) {
                    Text(
                        text = galleryItemData.author,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(fontSize = 22.sp),
                        color = Color.Black
                    )
                    CompositionLocalProvider(
                        LocalContentAlpha provides ContentAlpha.medium
                    ) {
                        Text(
                            text = "Height = ${galleryItemData.height}, Width = ${galleryItemData.width}",
                            style = MaterialTheme.typography.body2,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(end = 25.dp)
                        )
                    }

                    IconButton(onClick = { toggleColor = !toggleColor }) {
                        Icon(
                            imageVector = if (toggleColor) Icons.Filled.MonochromePhotos else Icons.Filled.PhotoCamera,
                            contentDescription = "icon"

                        )
                    }

                }


                IconButton(onClick = {
                    expanded = !expanded
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                }) {
                    Icon(
                        imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                        contentDescription = "expand icon"

                    )
                }


            }

            if (expanded) {
                Text(
                    text = ("Composem ipsum color sit lazy, " +
                            "padding theme elit, sed do bouncy. ").repeat(8),
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                )
            }


        }


    }
}