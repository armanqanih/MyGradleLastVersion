package org.lotka.xenonx.presentation.ui.app

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold

import androidx.compose.runtime.Composable

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.pager.ExperimentalPagerApi


import org.lotka.xenonx.presentation.ui.navigation.ScreenNavigation
import org.lotka.xenonx.presentation.ui.screen.detail.DetailScreen
import org.lotka.xenonx.presentation.ui.screen.detail.DetailViewModel
import org.lotka.xenonx.presentation.ui.screen.home.HomeScreen
import org.lotka.xenonx.presentation.ui.screen.home.HomeViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun HomeApp(
    activity: HomeActivity,
    navController: NavHostController,
    onNavigateToRecipeDetailScreen: (String) -> Unit,
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit,
    keyboardController: SoftwareKeyboardController,
) {


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {

        },
        content = { paddingValues ->
            val bottomPadding = paddingValues.calculateBottomPadding()
            NavHost(
                navController = navController,
                startDestination = ScreenNavigation.HomeRoutScreen.route,
                modifier = Modifier.padding(bottom = bottomPadding)
            ) {
                composable(route = ScreenNavigation.HomeRoutScreen.route) { backStackEntry ->

                    HomeScreen(navController = navController)
                }
                composable(route = ScreenNavigation.DetailRoutScreen.route + "/{coinId}") {
                    DetailScreen()
                }
                composable(route = ScreenNavigation.BookMarkRoutScreen.route) {
                    // BookmarkScreen composable should be added here
                }
            }
        }
    )
}

@Composable
fun OnBackClickStateSaver(navController: NavController) {
    BackHandler(true) {
        navigateToTab(navController, ScreenNavigation.HomeRoutScreen.route)
    }
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screenRoute ->
            popUpTo(screenRoute) { saveState = true }
        }
        launchSingleTop = true
        restoreState = true
    }
}
