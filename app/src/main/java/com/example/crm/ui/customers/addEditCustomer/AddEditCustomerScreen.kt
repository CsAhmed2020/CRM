package com.example.crm.ui.customers.addEditCustomer

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.crm.R
import com.example.crm.ui.customers.addEditCustomer.component.TransparentHintTextField

@Composable
fun AddEditCustomerScreen(
    navController: NavController,
    addEditCViewModel: AddEditCViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val activity = context as Activity?

    val cNameState = addEditCViewModel.cName.value
    val cCompanyState = addEditCViewModel.cCompany.value
    val cEmailState = addEditCViewModel.cEmail.value
    val cPhoneState = addEditCViewModel.cPhone.value
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.customer_details), color = Color.White)
                },
                actions = {
                    Text(
                        text = "Save",
                        color = Color.White,
                        modifier = Modifier
                            .padding(end = 15.dp)
                            .clickable {
                                addEditCViewModel.onEvent(AddEditCustomerEvent.SaveCustomer)
                            }
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        activity!!.onBackPressed()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp, vertical = 12.dp)
            .verticalScroll(rememberScrollState())) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = "*", color = Color.Red, style = MaterialTheme.typography.subtitle2,
                modifier = Modifier.padding(end = 2.dp))
                TransparentHintTextField(
                    text = cNameState.text,
                    hint = cNameState.hint,
                    onValueChange = {
                        addEditCViewModel.onEvent(AddEditCustomerEvent.EnteredName(it))
                    },
                    onFocusChange = {
                        addEditCViewModel.onEvent(AddEditCustomerEvent.ChangeNameFocus(it))
                    },
                    isHintVisible = cNameState.isHintVisible,
                    singleLine = true,
                    color = Color.Black,
                    textStyle = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Box(
                modifier = Modifier
                    .padding(start = 5.dp, top = 10.dp, end = 5.dp)
                    .size(300.dp, 0.5.dp)
                    .background(Color.Gray)
            )
                TransparentHintTextField(
                    text = cCompanyState.text,
                    hint = cCompanyState.hint,
                    onValueChange = {
                        addEditCViewModel.onEvent(AddEditCustomerEvent.EnteredCompany(it))
                    },
                    onFocusChange = {
                        addEditCViewModel.onEvent(AddEditCustomerEvent.ChangeCompanyFocus(it))
                    },
                    isHintVisible = cCompanyState.isHintVisible,
                    singleLine = true,
                    color = Color.Black,
                    textStyle = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.fillMaxWidth()
                )
            Box(
                modifier = Modifier
                    .padding(start = 5.dp, top = 10.dp, end = 5.dp)
                    .size(300.dp, 0.5.dp)
                    .background(Color.Gray)
            )
                TransparentHintTextField(
                    text = cEmailState.text,
                    hint = cEmailState.hint,
                    onValueChange = {
                        addEditCViewModel.onEvent(AddEditCustomerEvent.EnteredEmail(it))
                    },
                    onFocusChange = {
                        addEditCViewModel.onEvent(AddEditCustomerEvent.ChangeEmailFocus(it))
                    },
                    isHintVisible = cEmailState.isHintVisible,
                    singleLine = true,
                    color = Color.Black,
                    textStyle = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.fillMaxWidth()
                )
            Box(
                modifier = Modifier
                    .padding(start = 5.dp, top = 10.dp, end = 5.dp)
                    .size(300.dp, 0.5.dp)
                    .background(Color.Gray)
            )
                TransparentHintTextField(
                    text = cPhoneState.text,
                    hint = cPhoneState.hint,
                    onValueChange = {
                        addEditCViewModel.onEvent(AddEditCustomerEvent.EnteredPhone(it))
                    },
                    onFocusChange = {
                        addEditCViewModel.onEvent(AddEditCustomerEvent.ChangePhoneFocus(it))
                    },
                    isHintVisible = cPhoneState.isHintVisible,
                    singleLine = true,
                    color = Color.Black,
                    textStyle = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }

