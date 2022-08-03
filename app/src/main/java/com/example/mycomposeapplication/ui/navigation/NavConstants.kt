package com.example.mycomposeapplication.ui.navigation

import androidx.annotation.StringRes
import com.example.mycomposeapplication.R


sealed class NavConstants(val route: String, @StringRes val resourceId: Int){
    object Home : NavConstants("HomeScreen", R.string.navigation_item_home)
    object Widgets : NavConstants("WidgetsScreen", R.string.navigation_item_widgets)
    object Animation : NavConstants("AnimationScreen", R.string.navigation_item_animation)
    object DemoUI : NavConstants("DemoScreen", R.string.navigation_item_demoui)
    object Template : NavConstants("TemplateScreen", R.string.navigation_item_template)
}

sealed class SubNavConstants(val route: String){
    object HomeDetail : SubNavConstants("HomeDetail")
    object StateExample : SubNavConstants("StateExample")


    object WidgetsDetail : SubNavConstants("WidgetDetail")
    object AnimationDetail : SubNavConstants("AnimationDetail")
    object DemoUIDetail : SubNavConstants("DemoDetail")
    object TemplateDetail : SubNavConstants("TemplateDetail")
}