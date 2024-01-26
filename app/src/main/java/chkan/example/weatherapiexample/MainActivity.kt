package chkan.example.weatherapiexample

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import chkan.example.weatherapiexample.ui.ShareViewModel
import chkan.example.weatherapiexample.ui.screens.details.DetailsScreen
import chkan.example.weatherapiexample.ui.screens.list.WeatherListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(){

    private val shareViewModel: ShareViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "list") {
                composable("list") {
                    WeatherListScreen(onNavigateToDetails = { navController.navigate("details/$it") }, shareViewModel = shareViewModel)
                }
                composable(
                    route = "details/{city}",
                    arguments = listOf(navArgument("city") { type = NavType.StringType })
                ) {
                    val city = it.arguments?.getString("city")
                    city?.let {
                        val forecastList = shareViewModel.getForecastDataByCity(city) ?: listOf()
                        DetailsScreen(city = city, onNavigateBack = { navController.popBackStack() }, forecastList = forecastList)
                    }
                }
            }
        }
    }
}