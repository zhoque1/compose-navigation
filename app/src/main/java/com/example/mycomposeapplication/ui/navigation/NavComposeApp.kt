package com.example.mycomposeapplication.ui.navigation

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mycomposeapplication.ui.screens.*
import com.example.mycomposeapplication.ui.theme.*


@Composable
fun NavComposeApp() {
    val navController = rememberNavController()



    NavHost(navController = navController, startDestination = NavConstants.Home.route) {
        //root screens
        composable(NavConstants.Home.route) { HomeScreen(navController) }
        composable(NavConstants.Widgets.route) { WidgetsScreen(navController) }
        composable(NavConstants.Animation.route) { AnimationScreen(navController) }
        composable(NavConstants.DemoUI.route) { DemoScreen(navController) }
        composable(NavConstants.Template.route) { TemplateScreen(navController) }

        //sub screens
        composable(SubNavConstants.HomeDetail.route) { HomeDetailScreen(navController) }
        composable(SubNavConstants.StateExample.route) { StateExampleScreen(navController) }


        composable(SubNavConstants.WidgetsDetail.route) { WidgetDetail(navController) }
        composable(SubNavConstants.AnimationDetail.route) { AnimationDetail(navController) }
        composable(SubNavConstants.DemoUIDetail.route) { DemoDetail(navController) }
        composable(SubNavConstants.TemplateDetail.route) { TemplateDetail(navController) }
    }
}


@Composable
fun ScaffoldScreen(navController: NavHostController, screen: @Composable () -> Unit) {

    val bottomNavigationItems = listOf(
        NavConstants.Home,
        NavConstants.Widgets,
        NavConstants.Animation,
        NavConstants.DemoUI,
        NavConstants.Template
    )


    Scaffold(topBar = {},
        bottomBar = { BottomAppNavBar(navController, bottomNavigationItems) },
        content = { screen() }
    )
}

@Composable
fun BottomAppNavBar(navController: NavHostController, bottomNavigationItems: List<NavConstants>) {

    CompositionLocalProvider(
        LocalRippleTheme provides ClearRippleTheme
    ) {
        BottomAppBar(
            backgroundColor = Color.White,
            contentColor = MaterialTheme.colors.primary,
            elevation = 10.dp,
        ) {
            bottomNavigationItems.forEach { screen ->
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                val isSelected =
                    currentDestination?.hierarchy?.any { it.route == screen.route } == true

                BottomNavigationItem(
                    icon = { Icon(imageVector = Icons.Filled.Search, contentDescription = null) },
                    label = {
                        if (isSelected) {
                            Text(stringResource(screen.resourceId), color = Black)
                        } else {
                            Text(stringResource(screen.resourceId))
                        }
                    },
                    selected = isSelected,
                    selectedContentColor = Turmeric,
                    unselectedContentColor = SilverChalice,
                    modifier = if (isSelected) Modifier.drawBehind {
                        val strokeWidth = 6.dp.toPx()
                        //draw line of width 150px on top (hence y = 0f)
                        drawLine(
                            Turmeric,
                            Offset((size.width / 2.0f) - 75f, 0f),
                            Offset((size.width / 2.0f) + 75f, 0f),
                            strokeWidth,
                            cap = StrokeCap.Round
                        )
                    } else Modifier,
                    onClick = {
                        navController.navigate(screen.route) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(navController.graph.startDestinationId)
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    }
                )
            }
        }
    }

}