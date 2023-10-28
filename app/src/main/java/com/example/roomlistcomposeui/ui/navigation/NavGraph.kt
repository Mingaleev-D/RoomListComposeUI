package com.example.roomlistcomposeui.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.roomlistcomposeui.ui.detail.DetailListScreen
import com.example.roomlistcomposeui.ui.list.ListScreen

/**
 * @author : Mingaleev D
 * @data : 28.10.2023
 */

@Composable
fun NavGraphSetup(
    navController: NavHostController
) {

   NavHost(navController = navController, startDestination = Screen.ListScreen.route) {
      composable(
          route = Screen.ListScreen.route
      ) {
         ListScreen(
             onFabClick = {
                navController.navigate(route = Screen.DetailScreen.route)
             },
             onItemClick = {
                navController.navigate(route = Screen.DetailScreen.passId(it))
             }
         )
      }

      composable(
          route = Screen.DetailScreen.route,
          arguments = listOf(navArgument(name = "id") {
             type = NavType.IntType
             defaultValue = -1
          })
      ) {
         DetailListScreen(
             onBackCkick = { navController.navigateUp() },
             onSaveClick = { navController.navigateUp() }
         )
      }
   }
}

sealed class Screen(val route: String) {
   object ListScreen : Screen("list_screen")
   object DetailScreen : Screen("detail_screen?id={id}") {

      fun passId(id: Int? = null): String {
         return "detail_screen?id=$id"
      }
   }
}