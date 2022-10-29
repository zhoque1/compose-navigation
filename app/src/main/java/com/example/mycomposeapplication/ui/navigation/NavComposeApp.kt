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
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mycomposeapplication.ui.screens.*
import com.example.mycomposeapplication.ui.GlobalViewModel
import com.example.mycomposeapplication.ui.theme.*
import org.koin.androidx.compose.viewModel
import timber.log.Timber


@Composable
fun NavComposeApp() {
    val navController = rememberNavController()
    val exampleOfRootViewModel: GlobalViewModel by viewModel()


    NavHost(navController = navController, startDestination = NavConstants.Home.route) {
        //root screens
        composable(NavConstants.Home.route) {
            Timber.d("event = getting call twoice Home")
            HomeScreen(navController)
        }
        composable(NavConstants.Widgets.route) {
            Timber.d("event = getting call twoice Widgets")
            WidgetsScreen(navController)
        }
        composable(NavConstants.Animation.route) {
            Timber.d("event = getting call twoice Animation")
            AnimationScreen(navController)
        }
        composable(NavConstants.DemoUI.route) {
            Timber.d("event = getting call twoice DemoUI")
            DemoScreen(navController)
        }
        composable(NavConstants.Template.route) {
            Timber.d("event = getting call twoice Template")
            TemplateScreen(navController)
        }

        //sub screens
        composable(SubNavConstants.HomeDetail.route) { HomeDetailScreen(navController, exampleOfRootViewModel) }
        composable(SubNavConstants.StateExample.route) { StateExampleScreen(navController) }


        composable(SubNavConstants.WidgetsDetail.route) { WidgetDetailScreen(navController, exampleOfRootViewModel) }
        composable(SubNavConstants.AnimationDetail.route) { AnimationDetailScreen(navController, exampleOfRootViewModel) }
        composable(SubNavConstants.DemoUIDetail.route) { DemoDetailScreen(navController, exampleOfRootViewModel) }
        composable(SubNavConstants.TemplateDetail.route) { TemplateDetailScreen(navController, exampleOfRootViewModel) }
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
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            bottomNavigationItems.forEach { screen ->
                val isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true

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
                            handleNavigationToTopDestination(this, navController)
                        }
                    }
                )
            }
        }
    }
}

/**
 * @param navOptionsBuilder handle Navigation To TopDestination that allows us to save the state of the screens
 * This is needed if you are navigating outside Bottom bar clicks to any bottom nav tabs
 * Example Home, Search and More
 */
fun handleNavigationToTopDestination(navOptionsBuilder: NavOptionsBuilder, navController: NavHostController) {
    // Pop up to the start destination of the graph to
    // avoid building up a large stack of destinations
    // on the back stack as users select items
    navOptionsBuilder.popUpTo(navController.graph.findStartDestination().id) {
        saveState = true
    }
    // Avoid multiple copies of the same destination when
    // reselecting the same item
    navOptionsBuilder.launchSingleTop = true
    // Restore state when reselecting a previously selected item
    navOptionsBuilder.restoreState = true
}