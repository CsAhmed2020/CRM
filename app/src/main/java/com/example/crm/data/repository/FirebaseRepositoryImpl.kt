package com.example.crm.data.repository

import android.util.Log
import com.example.crm.R
import com.example.crm.data.DataStateResult
import com.example.crm.util.Constants
import com.example.crm.domain.model.Customer
import com.example.crm.domain.repository.FirebaseRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.util.UUID

class FirebaseRepositoryImpl(
    private val firebaseFireStore: FirebaseFirestore
): FirebaseRepository {
    override suspend fun addCustomer(customer: Customer): DataStateResult<Unit> {
        val cID = UUID.randomUUID().toString()
        customer.cID = cID
        var result: DataStateResult<Unit> = DataStateResult.Loading()
        result = try {
            firebaseFireStore.collection(Constants.COLLECTION_CUSTOMERS)
                .document("UID123")
                .collection(Constants.COLLECTION_USER_CUSTOMERS)
                .document(cID)
                .set(customer)
                .await()
            DataStateResult.Success()
        }catch (e:Exception){
            DataStateResult.Error(R.string.error_add_customer)
        }
        return result
    }

    override suspend fun getCustomers() =
        callbackFlow<DataStateResult<List<Customer>>> {
            var snapshotListener: ListenerRegistration? = null
            try {
                val customers = mutableListOf<Customer>()
                val docReference = firebaseFireStore.collection(Constants.COLLECTION_CUSTOMERS)
                    .document("UID123").collection(Constants.COLLECTION_USER_CUSTOMERS)

                snapshotListener = docReference.addSnapshotListener { querySnapshot, _ ->
                    querySnapshot?.let {
                        if (customers.isNotEmpty()) {
                            customers.clear()
                        }
                        for (document in it.documents) {
                            val customer = document.toObject(Customer::class.java)
                            customers.add(customer!!)
                        }

                        trySend(DataStateResult.Success(data = customers))
                    }

                }
            } catch (e: Exception) {
                trySend(DataStateResult.Error())
            }
            awaitClose { snapshotListener?.remove() }
        }

    override suspend fun getCustomer(cId:String): Customer? {
        return try {
            var customer: Customer? = null
            val documentSnapshot = firebaseFireStore.collection(Constants.COLLECTION_CUSTOMERS)
                .document("UID123").collection(Constants.COLLECTION_USER_CUSTOMERS)
                .document(cId).get().await()
            documentSnapshot?.let {
                customer = it.toObject(Customer::class.java)
            }
            customer
        } catch (e: Exception) {
            Log.d("Ahmed", "Error: getCustomer(): ${e.message}")
            return null
        }
    }

}