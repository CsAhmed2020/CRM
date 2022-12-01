package com.example.crm.domain.useCases.customers

import com.example.crm.domain.repository.FirebaseRepository
import com.example.crm.domain.model.Customer
import com.example.crm.domain.model.InvalidNoteException

class AddCustomer(
    private val repository: FirebaseRepository
) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(customer: Customer) {
        if(customer.cName!!.isEmpty()) {
            throw InvalidNoteException("Please enter Name!")
        }
        repository.addCustomer(customer)
    }
}