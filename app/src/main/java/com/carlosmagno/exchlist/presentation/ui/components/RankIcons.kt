package com.carlosmagno.exchlist.presentation.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.carlosmagno.exchlist.R
import com.carlosmagno.exchlist.presentation.ui.theme.Dimens

@Composable
fun RankIcons(rank: Int, maxVisible: Int = 10) {
    val iconCount = rank.coerceAtMost(maxVisible)

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = stringResource(R.string.rank_label),
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(end = Dimens.paddingScreen4)
        )

        repeat(iconCount) {
            Icon(
                painter = painterResource(id = R.drawable.ic_stars),
                contentDescription = stringResource(R.string.rank_icon_desc),
                modifier = Modifier
                    .size(Dimens.spacingScreen16)
                    .padding(end = Dimens.spacingScreen2)
            )
        }
    }
}