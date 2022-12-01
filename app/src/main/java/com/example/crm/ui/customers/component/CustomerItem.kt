package com.example.crm.ui.customers.component

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.crm.domain.model.Customer
import com.example.crm.util.CallUtil.dealPhoneNumber

@Composable
fun CustomerItem(context: Context,customer: Customer, onItemClick: () -> Unit) {

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp),
    shape = RoundedCornerShape(5.dp),
        elevation = 5.dp
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {
                onItemClick.invoke()
            }) {
            if (!customer.cName.isNullOrEmpty()) {
                Text(text = customer.cName.toString(),
                style = MaterialTheme.typography.h5)
            }
            Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                if (!customer.cCompany.isNullOrEmpty()) {
                        Text(
                            text = customer.cCompany.toString(),
                            style = MaterialTheme.typography.h6,
                        )
                    if (!customer.cPhone.isNullOrEmpty()){
                        IconButton(onClick = {
                            context.dealPhoneNumber(customer.cPhone.toString())
                        }) {
                            Icon(imageVector = Icons.Default.Call, contentDescription = "call")
                        }
                    }
                }
            }
            if (!customer.cEmail.isNullOrEmpty()) {
                Text(text = customer.cEmail.toString(),
                    style = MaterialTheme.typography.h6)
            }
        }
    }
}