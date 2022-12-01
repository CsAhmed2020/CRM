package com.example.crm.navigation

sealed class Screen(val route: String) {

    object HomeScreen : Screen("home_screen")
    object CustomersScreen : Screen("customers_screen")
    object AddEditCustomerScreen : Screen("add_edit_customer_screen")
    object CallsScreen : Screen("calls_screen")
    object MeetingsScreen : Screen("meetings_screen")
    object TasksScreen : Screen("tasks_screen")
    }


