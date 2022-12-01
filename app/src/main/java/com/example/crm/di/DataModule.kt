package com.example.crm.di



import com.example.crm.domain.useCases.customers.GetCustomer
import com.example.crm.domain.useCases.customers.GetCustomers
import com.example.crm.domain.repository.FirebaseRepository
import com.example.crm.data.repository.FirebaseRepositoryImpl
import com.example.crm.domain.useCases.customers.AddCustomer
import com.example.crm.domain.useCases.customers.CustomersUseCases
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

   /* @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }*/

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseRepository(
        /*firebaseAuth: FirebaseAuth,*/
        firebaseFirestore: FirebaseFirestore
    ): FirebaseRepository {
        return FirebaseRepositoryImpl(/*firebaseAuth,*/ firebaseFirestore)
    }

    @Provides
    @Singleton
    fun provideCustomersUseCases(repository: FirebaseRepository): CustomersUseCases {
        return CustomersUseCases(
            getCustomer = GetCustomer(repository),
            getCustomers = GetCustomers(repository),
            addCustomer = AddCustomer(repository)
        )
    }
}