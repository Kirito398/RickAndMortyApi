package com.sir.entity.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import com.sir.entity.ui.theme.AppTheme

@Composable
fun Search(
    value: String,
    onValueChanged: (String) -> Unit,
    onSearch: () -> Unit,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        singleLine = true,
        textStyle = AppTheme.typography.titleMedium,
        shape = RoundedCornerShape(size = AppTheme.dimensions.rounderCornerSize),
        colors = TextFieldDefaults.colors().copy(
            focusedTextColor = AppTheme.colors.primaryTextColor,
            unfocusedTextColor = AppTheme.colors.primaryTextColor,
            focusedContainerColor = AppTheme.colors.primaryTintTextColor,
            unfocusedContainerColor = AppTheme.colors.primaryTintTextColor,
            cursorColor = AppTheme.colors.primaryTextColor,
            focusedIndicatorColor = AppTheme.colors.primaryTextColor
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(AppTheme.dimensions.defaultPadding),
        trailingIcon = {
            if (value.isNotEmpty()) {
                Icon(
                    Icons.Rounded.Clear,
                    contentDescription = "Clear",
                    tint = AppTheme.colors.primaryTextColor,
                    modifier = Modifier.clickable(
                        role = Role.Button
                    ) {
                        onValueChanged("")
                    }
                )
            }
        },
        leadingIcon = {
            Icon(
                Icons.Rounded.Search,
                contentDescription = "Search",
                tint = AppTheme.colors.primaryTextColor
            )
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = { onSearch() }
        )
    )
}