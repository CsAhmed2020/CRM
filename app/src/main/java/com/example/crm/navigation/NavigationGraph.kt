package com.example.crm.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.crm.navigation.Screen
import com.example.crm.ui.customers.CustomersScreen
import com.example.crm.ui.customers.addEditCustomer.AddEditCustomerScreen
import com.example.crm.ui.home.HomeScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable


@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun NavigationGraph(navController: NavHostController) {
    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(route = Screen.HomeScreen.route) {
           HomeScreen(navController = navController)
        }
        composable(route = Screen.CustomersScreen.route) {
            CustomersScreen(navController = navController)
        }
        composable(route = Screen.MeetingsScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.TasksScreen.route){
            HomeScreen(navController = navController)
        }
        composable(route = Screen.AddEditCustomerScreen.route +"?cId={cId}",
        arguments = listOf(
            navArgument(name = "cId"){
                type = NavType.StringType
                defaultValue = ""
            }
        )
            ){
            AddEditCustomerScreen(navController = navController)
        }
    }
}
