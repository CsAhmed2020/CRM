package com.example.crm.ui.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.crm.R
import com.example.crm.navigation.NavigationDrawer
import com.example.crm.ui.customers.component.ErrorInfo


@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun HomeScreen(navController: NavHostController) {

    val scaffoldState = rememberScaffoldState()

    Scaffold(modifier = Modifier.fillMaxSize()) {
        NavigationDrawer(navController = navController)
        BodyContent(scaffoldState = scaffoldState, navController = navController)
    }
}

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun BodyContent(scaffoldState: ScaffoldState, navController: NavHostController) {
    var navigateClick by remember { mutableStateOf(false) }
    val offSetAnim by animateDpAsState(targetValue = if (navigateClick) 250.dp else 0.dp)
    val scaleAnim by animateFloatAsState(targetValue = if (navigateClick) 0.6f else 1.0f)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .scale(scaleAnim)
            .offset(x = offSetAnim)
            .clip(if (navigateClick) RoundedCornerShape(30.dp) else RoundedCornerShape(0.dp))
            .background(MaterialTheme.colors.background)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.primary),
            horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                IconButton(onClick = {
                    navigateClick = !navigateClick
                }) {
                    Icon(imageVector = Icons.Default.Menu, contentDescription = "navigate",
                    tint =  MaterialTheme.colors.onPrimary)
                }
                Text(
                    text = stringResource(id = R.string.home),
                    style = MaterialTheme.typography.h5,
                    color =  MaterialTheme.colors.onPrimary,
                    modifier = Modifier.padding(top = 5.dp)
                )
            }
        }

        Column(modifier = Modifier
            .fillMaxSize()) {

            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
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
                                .size(80.dp))

                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Ahmed Abdelrahman",
                        color =  Color.Black,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "csahmed.98.20@gmail.com", color =  Color.Black)
                    Spacer(modifier = Modifier.height(10.dp))
                    Box(modifier = Modifier.fillMaxWidth()) {
                        ErrorInfo(iconResId = R.drawable.ic_baseline_calendar_month_24, messageResId =R.string.no_activites, errorMessageResId = R.string.add_more)
                    }
                }

            }

        }

    }

}