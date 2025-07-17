package com.carlosmagno.exchlist.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.carlosmagno.exchlist.formatLargeNumber
import com.carlosmagno.exchlist.presentation.ui.theme.Dimens
import com.carlosmagno.exchlist.presentation.ui.theme.LightGreen

@Composable
fun VolumeItem(label: String, value: Double?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Dimens.paddingScreen4),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, style = MaterialTheme.typography.bodyMedium)
        Text(
            text = value?.let { formatLargeNumber(it) } ?: "N/A",
            fontWeight = FontWeight.Bold,
            color = LightGreen
        )
    }
}