package com.example.crm.domain.useCases.customers

import com.example.crm.domain.repository.FirebaseRepository
import com.example.crm.domain.model.Customer


class GetCustomer(
    private val repository: FirebaseRepository
) {
    suspend operator fun invoke(id: String): Customer? {
        return repository.getCustomer(id)
    }
}