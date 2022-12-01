package com.example.crm.ui.customers

import com.example.crm.domain.model.Customer

data class CustomersState(
    val customers: List<Customer> = emptyList()
)
