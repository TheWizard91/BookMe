package com.example.myapplication.classes

import androidx.navigation.NavController
import com.example.myapplication.objects.Destinations

class Actions(navController: NavController) {
    val openTask: (Int) -> Unit = { taskId -> navController.navigate("${Destinations.TaskDetailArgs}/${taskId}") }
    val addHome: () -> Unit = { navController.navigate(Destinations.Home) }
    val addNotifications: () -> Unit = { navController.navigate(Destinations.Notifications) }
    val addFavorites: () -> Unit = { navController.navigate(Destinations.Favorites) }
    val addShopping: () -> Unit = { navController.navigate(Destinations.Shopping) }
    val navigateBack: () -> Unit = { navController.popBackStack() }
}