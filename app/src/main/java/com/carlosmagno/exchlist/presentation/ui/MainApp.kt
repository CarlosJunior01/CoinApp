import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.carlosmagno.exchlist.domain.model.Exchange
import com.carlosmagno.exchlist.navigation.Routes
import com.carlosmagno.exchlist.presentation.ui.screen.ExchangeDetailScreen
import com.carlosmagno.exchlist.presentation.ui.screen.ExchangeListScreen
import com.carlosmagno.exchlist.presentation.ui.screen.FavoriteScreen
import com.carlosmagno.exchlist.presentation.viewmodel.FavoriteViewModel
import com.google.gson.Gson
import org.koin.androidx.compose.getViewModel

@Composable
fun MainApp() {
    val navController = rememberNavController()
    val gson = remember { Gson() }

    NavHost(navController, startDestination = Routes.LIST) {
        composable(Routes.LIST) {
            ExchangeListScreen(navController, onExchangeClick = { selected: Exchange ->
                val json = Uri.encode(gson.toJson(selected))
                navController.navigate("${Routes.DETAIL}/$json")
            })
        }

        composable(
            "${Routes.DETAIL}/{${Routes.ARG_EXCHANGE}}",
            arguments = listOf(navArgument(Routes.ARG_EXCHANGE) { type = NavType.StringType })
        ) { backStackEntry ->

            val favoritesViewModel: FavoriteViewModel = getViewModel()

            val json = backStackEntry.arguments?.getString(Routes.ARG_EXCHANGE)
            val exchange = gson.fromJson(json, Exchange::class.java)

            ExchangeDetailScreen(
                exchange = exchange,
                isFavorite = favoritesViewModel.isFavorite(exchange.id),
                onToggleFavorite = { favoritesViewModel.toggleFavorite(exchange) }
            )
        }

        composable(Routes.FAVORITES) {
            FavoriteScreen(
                onExchangeClick = { selected ->
                    val json = Uri.encode(gson.toJson(selected))
                    navController.navigate("${Routes.DETAIL}/$json")
                }
            )
        }
    }
}