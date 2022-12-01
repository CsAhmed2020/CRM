package com.example.crm.ui.customers.addEditCustomer

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crm.domain.model.Customer
import com.example.crm.domain.model.InvalidNoteException
import com.example.crm.domain.useCases.customers.CustomersUseCases
import com.example.crm.ui.customers.addEditCustomer.component.TextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditCViewModel @Inject constructor(
    private val customersUseCases: CustomersUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _cName = mutableStateOf(
        TextFieldState(
            hint = "* Customer Name..."
        )
    )
    val cName: State<TextFieldState> = _cName

    private val _cCompany = mutableStateOf(
        TextFieldState(
            hint = "Customer Company..."
        )
    )
    val cCompany: State<TextFieldState> = _cCompany

    private val _cEmail = mutableStateOf(
        TextFieldState(
            hint = "Customer Company..."
        )
    )
    val cEmail: State<TextFieldState> = _cEmail

    private val _cPhone = mutableStateOf(
        TextFieldState(
            hint = "Customer Company..."
        )
    )
    val cPhone: State<TextFieldState> = _cPhone

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentCustomerId: String? = null

    init {
        savedStateHandle.get<String>("cId")?.let { cId ->
            Log.d("Ahmed",cId)
            Log.d("Ahmed2",cId.isNotEmpty().toString())
            if(cId.isNotEmpty()) {
                viewModelScope.launch {
                    customersUseCases.getCustomer(cId)?.also { customer ->
                        currentCustomerId = customer.cID
                        _cName.value = cName.value.copy(
                            text = customer.cName.toString(),
                            isHintVisible = false
                        )

                        _cCompany.value = cCompany.value.copy(
                            text = customer.cCompany.toString(),
                            isHintVisible = false
                        )

                        _cEmail.value = cEmail.value.copy(
                            text = customer.cEmail.toString(),
                            isHintVisible = false
                        )

                        _cPhone.value = cPhone.value.copy(
                            text = customer.cPhone.toString(),
                            isHintVisible = false
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditCustomerEvent){
        when(event){
            is AddEditCustomerEvent.EnteredName -> {
                _cName.value = cName.value.copy(
                    text = event.value
                )
            }
            is AddEditCustomerEvent.EnteredCompany -> {
                _cCompany.value = cCompany.value.copy(
                    text = event.value
                )
            }
            is AddEditCustomerEvent.EnteredEmail -> {
                _cEmail.value = cEmail.value.copy(
                    text = event.value
                )
            }
            is AddEditCustomerEvent.EnteredPhone -> {
                _cPhone.value = cPhone.value.copy(
                    text = event.value
                )
            }
            is AddEditCustomerEvent.ChangeNameFocus -> {
                _cName.value = _cName.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            _cName.value.text.isBlank()
                )
            }
            is AddEditCustomerEvent.ChangeCompanyFocus -> {
                _cCompany.value = _cCompany.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            _cCompany.value.text.isBlank()
                )
            }
            is AddEditCustomerEvent.ChangeEmailFocus -> {
                _cEmail.value = _cEmail.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            _cEmail.value.text.isBlank()
                )
            }
            is AddEditCustomerEvent.ChangePhoneFocus -> {
                _cPhone.value = _cPhone.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            _cPhone.value.text.isBlank()
                )
            }
            AddEditCustomerEvent.SaveCustomer -> {
                viewModelScope.launch {
                    try {
                        customersUseCases.addCustomer(
                            Customer(
                                cName = cName.value.text,
                                cCompany = cCompany.value.text,
                                cEmail = cEmail.value.text,
                                cPhone = cPhone.value.text
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveNote)
                    } catch(e: InvalidNoteException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save customer"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
        object SaveNote: UiEvent()
    }
}