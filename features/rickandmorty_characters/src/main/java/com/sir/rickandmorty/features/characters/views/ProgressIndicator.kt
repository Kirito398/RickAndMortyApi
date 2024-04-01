package com.sir.rickandmorty.features.characters.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.sir.entity.ui.theme.AppTheme
import com.sir.rickandmorty.features.characters.R

@Preview
@Composable
internal fun ProgressIndicator() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(AppTheme.dimensions.defaultPadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            color = AppTheme.colors.primaryTintTextColor,
            strokeWidth = AppTheme.dimensions.progressIndicatorStrokeWidth,
            modifier = Modifier
                .size(AppTheme.dimensions.progressIndicatorSize)
        )
        Spacer(modifier = Modifier.size(AppTheme.dimensions.spacerSize))
        Text(text = stringResource(R.string.updating), color = AppTheme.colors.primaryTintTextColor)
    }
}
