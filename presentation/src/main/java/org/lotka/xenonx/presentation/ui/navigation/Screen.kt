package org.lotka.xenonx.presentation.ui.navigation

sealed class ScreenNavigation(val route: String) {

    object DetailRoutScreen : ScreenNavigation(route = "Detail_Screen")
    object BookMarkRoutScreen : ScreenNavigation(route = "BookMark_Screen")
    object HomeRoutScreen : ScreenNavigation(route = "Home_Screen")
    object SearchRouteScreen : ScreenNavigation(route = "Search_Screen")


}