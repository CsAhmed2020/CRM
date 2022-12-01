package com.example.crm.domain.useCases.customers


import com.example.crm.data.DataStateResult
import com.example.crm.domain.repository.FirebaseRepository
import com.example.crm.domain.model.Customer
import kotlinx.coroutines.flow.Flow


class GetCustomers(
    private val repository: FirebaseRepository
) {

    suspend operator fun invoke(
    ): Flow<DataStateResult<List<Customer>>> {
        return repository.getCustomers()
        }
    }
