package com.example.crm.ui.customers.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun ErrorInfo(
    @DrawableRes iconResId: Int,
    @StringRes messageResId: Int,
    @StringRes errorMessageResId: Int,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Icon(
            imageVector = ImageVector.vectorResource(id = iconResId),
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier.size(100.dp)
        )
        Text(
            text = stringResource(id = messageResId),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(all = 5.dp)
        )
        Text(
            text = stringResource(id = errorMessageResId),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier.padding(all = 5.dp)
        )
    }
}

@Composable
fun EmptyListInfo(
    @DrawableRes iconResId: Int,
    @StringRes messageResId: Int,
    @StringRes errorMessageResId: Int,
) {
    ErrorInfo(
        iconResId = iconResId,
        messageResId = messageResId,
        errorMessageResId = errorMessageResId,
    )
}