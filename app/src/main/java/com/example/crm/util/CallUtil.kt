package com.example.crm.util

import android.content.Context
import android.content.Intent
import android.net.Uri

object CallUtil {


    fun Context.dealPhoneNumber(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$phoneNumber")
        startActivity(intent)
    }
}