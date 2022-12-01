package com.example.crm.domain.repository

import com.example.crm.data.DataStateResult
import com.example.crm.domain.model.Customer
import kotlinx.coroutines.flow.Flow

interface FirebaseRepository {

    suspend fun addCustomer(customer: Customer):DataStateResult<Unit>

    suspend fun getCustomers(): Flow<DataStateResult<List<Customer>>>

    suspend fun getCustomer(cId:String):Customer?
}