package com.example.crm.ui.customers

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.crm.data.DataStateResult
import com.example.crm.R
import com.example.crm.navigation.NavigationDrawer
import com.example.crm.navigation.Screen
import com.example.crm.ui.customers.component.CustomerItem
import com.example.crm.ui.customers.component.EmptyListInfo
import com.example.crm.ui.customers.component.LoadingProgressIndicator

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun CustomersScreen(
    navController: NavHostController
){
    val scaffoldState = rememberScaffoldState()

    Scaffold(modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Screen.AddEditCustomerScreen.route)
            },
                backgroundColor = MaterialTheme.colors.primary)
            {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "AddCustomer", tint =  MaterialTheme.colors.onPrimary)
            }
        }) {
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

    val customersViewModel :CustomersViewModel = hiltViewModel()

    val customersListState by customersViewModel.customersListState.observeAsState()

    var isRequestFinished by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        if (!isRequestFinished) {
            customersViewModel.getCustomers()
        }
    }

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
                .background(color =  MaterialTheme.colors.primary),
            horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                IconButton(onClick = {
                        navigateClick = !navigateClick
                }) {
                    Icon(imageVector = Icons.Default.Menu,
                        tint =  MaterialTheme.colors.onPrimary, contentDescription = "navigate")
                }
                Text(
                    text = stringResource(id = R.string.customers),
                    style = MaterialTheme.typography.h5,
                    color =  MaterialTheme.colors.onPrimary,
                    modifier = Modifier.padding(top = 5.dp)
                )
            }
        }

        Column(modifier = Modifier
            .fillMaxSize()) {
            when(customersListState){
                is DataStateResult.Error -> isRequestFinished = true
                is DataStateResult.Loading -> LoadingProgressIndicator()
                is DataStateResult.Success -> {
                    isRequestFinished = true
                    customersListState?.let { result ->
                        val customers = result.data!!
                        if (customers.isNotEmpty()){
                            LazyColumn(modifier = Modifier.fillMaxSize()){
                                itemsIndexed(customers){_,customer ->
                                    CustomerItem(
                                        context = context,
                                        customer = customer,
                                        onItemClick = {
                                            navController.navigate(Screen.AddEditCustomerScreen.route + "?cId=${customer.cID}")
                                        })

                                }
                            }
                        }else{
                            EmptyListInfo(
                                iconResId = R.drawable.ic_baseline_playlist_remove_24,
                                messageResId = R.string.no_available,
                                errorMessageResId = R.string.empty_list_desc)
                        }
                    }
                }
                else -> {}
            }
        }

    }

}
