package com.example.crm.navigation


import androidx.compose.foundation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.crm.R




@Composable
fun NavigationDrawer(navController: NavHostController) {

    Box(modifier = Modifier.fillMaxSize()
        .background(color =  MaterialTheme.colors.primary)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Column(modifier = Modifier
                    .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.TopCenter
                    ){
                        Image(painter = painterResource(id =R.drawable.avatar), contentDescription = "profile Image",
                            modifier = Modifier
                                .border(width = 2.dp, color = Color.White, shape = CircleShape)
                                .size(60.dp))

                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(text = "Ahmed Abdelrahman",
                        color =  MaterialTheme.colors.onPrimary,
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(text = "csahmed.98.20@gmail.com",
                        color =  MaterialTheme.colors.onPrimary)
                }

            }
            NavigationItem(
                resId = R.drawable.ic_baseline_home_24,
                text = stringResource(R.string.home),
            ) {
                navController.navigate(route = Screen.HomeScreen.route)
            }
            NavigationItem(
                resId = R.drawable.ic_baseline_person_24,
                text = stringResource(R.string.customers)
            ) {
                navController.navigate(route = Screen.CustomersScreen.route)
            }
            NavigationItem(
                resId = R.drawable.ic_baseline_call_24,
                text = stringResource(R.string.calls)
            ) {
                navController.navigate(route = Screen.CallsScreen.route)
            }
            NavigationItem(
                resId = R.drawable.ic_baseline_calendar_month_24,
                text = stringResource(id = R.string.meetings)
            ) {
                navController.navigate(route = Screen.MeetingsScreen.route)
            }
            NavigationItem(
                resId = R.drawable.ic_baseline_add_task_24,
                text = stringResource(R.string.tasks),
                topPadding = 20.dp
            ) {
                navController.navigate(route = Screen.TasksScreen.route)
            }

        }

    }


}