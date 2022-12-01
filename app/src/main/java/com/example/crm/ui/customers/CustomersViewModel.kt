package com.example.crm.ui.customers

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crm.data.DataStateResult
import com.example.crm.domain.repository.FirebaseRepository
import com.example.crm.domain.model.Customer
import com.example.crm.domain.useCases.customers.CustomersUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomersViewModel @Inject constructor(
    private val customersUseCases: CustomersUseCases,
):ViewModel() {

    private val _state = mutableStateOf(CustomersState())
    val state: State<CustomersState> = _state

    private val _customersListState = MutableLiveData<DataStateResult<List<Customer>>>()
    val customersListState: MutableLiveData<DataStateResult<List<Customer>>> =
        _customersListState

    private val _dataStateResult = MutableLiveData<DataStateResult<Any>>()
    val dataStateResult: MutableLiveData<DataStateResult<Any>> = _dataStateResult

    fun getCustomers(){
        viewModelScope.launch {
            _customersListState.postValue(DataStateResult.Loading())
            customersUseCases.getCustomers().collect {result ->
                when(result){
                    is DataStateResult.Error -> _customersListState.postValue(DataStateResult.Error())
                    is DataStateResult.Success -> _customersListState.postValue(result)
                    else -> {}
                }
            }
        }
    }
}