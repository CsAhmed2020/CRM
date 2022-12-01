package com.example.crm.ui.customers.addEditCustomer

import androidx.compose.ui.focus.FocusState

sealed class AddEditCustomerEvent{
    data class EnteredName(val value: String): AddEditCustomerEvent()
    data class ChangeNameFocus(val focusState: FocusState): AddEditCustomerEvent()
    data class EnteredCompany(val value: String): AddEditCustomerEvent()
    data class ChangeCompanyFocus(val focusState: FocusState): AddEditCustomerEvent()
    data class EnteredEmail(val value: String): AddEditCustomerEvent()
    data class ChangeEmailFocus(val focusState: FocusState): AddEditCustomerEvent()
    data class EnteredPhone(val value: String): AddEditCustomerEvent()
    data class ChangePhoneFocus(val focusState: FocusState): AddEditCustomerEvent()
    object SaveCustomer: AddEditCustomerEvent()
}

