package app.tinks.weather.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import app.tinks.weather.citydetail.CityDetailRoute
import app.tinks.weather.citylist.CityListRoute

const val listRoute = "list_route"
const val detailRoute = "detail_route"

@Composable
fun MyNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = listRoute
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        composable(route = listRoute) { CityListRoute(navController) }
        composable(
            route = "$detailRoute/{cityId}",
            arguments = listOf(navArgument("cityId") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            CityDetailRoute(
                navController,
                backStackEntry.arguments?.getString("cityId")
            )
        }
    }
}

fun NavController.navigateToCity(id: String, navOptions: NavOptions? = null) {
    this.navigate("$detailRoute/{$id}", navOptions)
}


