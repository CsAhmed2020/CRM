package com.example.crm.domain.model

data class Customer(
     var cID:String ?= "",
     var cName:String ?= "",
     var cCompany:String ?= "",
     var cEmail:String ?= "",
     var cPhone:String ?= "",
)

class InvalidNoteException(message: String): Exception(message)
