import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.rounded.Bookmark
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Movie
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Upcoming
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import org.lotka.xenonx.presentation.R
import org.lotka.xenonx.presentation.ui.navigation.ScreenNavigation
import org.lotka.xenonx.presentation.ui.screen.home.HomeEvent
import org.lotka.xenonx.presentation.ui.screen.home.HomeViewModel

@Composable
fun BottomNavigationBar(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
) {

    val items = listOf(
        BottomItem(
            title = stringResource(R.string.Home),
            selectIcon = Icons.Filled.Home,
            unSelectIcon = Icons.Outlined.Home
        ),
        BottomItem(
            title = stringResource(R.string.search),
            selectIcon = Icons.Filled.Search,
            unSelectIcon = Icons.Outlined.Search
        ),
        BottomItem(
            title = stringResource(R.string.bookmark),
            selectIcon = Icons.Filled.Bookmark,
            unSelectIcon = Icons.Outlined.Bookmark
        )
    )

    val selectedItem = rememberSaveable {
        mutableIntStateOf(0)
    }

    NavigationBar {
        Row(
            modifier = Modifier.background(MaterialTheme.colorScheme.inverseOnSurface)
        ) {
            items.forEachIndexed { index, bottomItem ->
                NavigationBarItem(
                    selected = selectedItem.intValue == index,
                    onClick = {
                        selectedItem.intValue = index
                        when (selectedItem.intValue) {
                            0 -> {
                                viewModel.onEvent(HomeEvent.navigate)
                                navController.popBackStack()
                                navController.navigate(ScreenNavigation.HomeRoutScreen.route)
                            }

                            1 -> {
                                viewModel.onEvent(HomeEvent.navigate)
                                navController.popBackStack()
                                navController.navigate(ScreenNavigation.SearchRouteScreen.route)
                            }

                            2 -> {
                                viewModel.onEvent(HomeEvent.navigate)
                                navController.popBackStack()
                                navController.navigate(ScreenNavigation.BookMarkRoutScreen.route)
                            }

                        }
                    }, icon = {
                        Icon(
                            imageVector = if(selectedItem.intValue == index)
                                    bottomItem.selectIcon else
                                    bottomItem.unSelectIcon,
                            contentDescription = bottomItem.title,
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }, label = {
                        Text(
                            text = bottomItem.title, color = MaterialTheme.colorScheme.onBackground
                        )
                    })
            }
        }
    }

}

data class BottomItem(
    val title: String,
    val selectIcon: ImageVector,
    val unSelectIcon: ImageVector
)
