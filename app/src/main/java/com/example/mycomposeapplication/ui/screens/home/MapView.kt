//package com.example.mycomposeapplication.ui.screens.home
//
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.geometry.Offset
//import androidx.compose.ui.platform.LocalContext
//import com.google.android.gms.maps.model.BitmapDescriptorFactory
//import com.google.android.gms.maps.model.CameraPosition
//import com.google.android.gms.maps.model.LatLng
//import com.google.maps.android.compose.*
//import com.google.maps.android.compose.MapType
//import com.homes.homesdotcom.ui.search.map.utils.SphericalUtil
//import com.residential.utility.getBitmap
//
//
//data class Pin(
//    val key: String,
//    val location: LatLng,
//    val resourceId: Int
//)
//
//
//enum class MapTypes(val type: Int) {
//    None(0),
//    Normal(1),
//    Satellite(2),
//    Terrain(3),
//    Hybrid(4)
//}
//
//
//@Composable
//fun MapView(modifier: Modifier = Modifier,
//            location: LatLng,                     // center location for the map view
//            zoomLevel: Float,                     // set zoom level
//            mapType: MapTypes,                    // map type from MapTypes
//            pins: List<Pin>,                      // markers of type
//            onMapLoaded: () -> Unit = {},         // listener when map has loaded
//            onMapClick: (LatLng) -> Unit = {},    // location when map is clicked
//            onMarkerClick: (String) -> Unit = {}  // selected marker -> returns marker id
//) {
//    val cameraPositionState = rememberCameraPositionState {
//        position = CameraPosition.fromLatLngZoom(location, zoomLevel)
//    }
//
//    val mapProperties by remember {
//        mutableStateOf(
//            MapProperties(
//                mapType = when (mapType) {
//                    MapTypes.Normal -> MapType.NORMAL
//                    MapTypes.Satellite -> MapType.SATELLITE
//                    MapTypes.Hybrid -> MapType.HYBRID
//                    MapTypes.Terrain -> MapType.HYBRID
//                    else -> MapType.NONE
//                }
//            )
//        )
//    }
//
//    val uiSettings by remember {
//        mutableStateOf(
//            MapUiSettings(
//                compassEnabled = false,
//                mapToolbarEnabled = false,
//                myLocationButtonEnabled = false,
//                zoomControlsEnabled = false
//            )
//        )
//    }
//
//    GoogleMap(
//        modifier = modifier,
//        cameraPositionState = cameraPositionState,
//        properties = mapProperties,
//        uiSettings = uiSettings,
//        onMapLoaded = {
//            onMapLoaded()
//        },
//        onMapClick = {
//            onMapClick(it)
//        }
//    ) {
//        if (pins.isNotEmpty()) {
//            for (pin in pins) {
//                getBitmap(LocalContext.current, pin.resourceId)?.let { bitmap ->
//                    val icon = BitmapDescriptorFactory.fromBitmap(bitmap)
//                    Marker(
//                        position = pin.location,
//                        anchor = Offset(0.5f, 1.0f),
//                        icon = icon,
//                        tag = pin.key,
//                        onClick = { marker ->
//                            onMarkerClick(marker.tag.toString())
//                            false
//                        }
//                    )
//                }
//            }
//        }
//    }
//}
