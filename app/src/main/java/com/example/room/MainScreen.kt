package com.example.room

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun MainScreen(viewModel: TodoViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "todo_list") {
        composable("todo_list") { TodoListPage(viewModel = viewModel, navController) }
        composable("new_page/{itemDes}",
            arguments = listOf(
                navArgument("itemDes") { type = NavType.StringType },

            )
        ) { backstack->
            val itemDes = backstack.arguments?.getString("itemDes")
            NewPage(itemDes)
        }
    }

}