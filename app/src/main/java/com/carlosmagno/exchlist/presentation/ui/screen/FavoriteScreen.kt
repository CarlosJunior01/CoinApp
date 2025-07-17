package com.carlosmagno.exchlist.presentation.ui.screen

import android.annotation.SuppressLint
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.carlosmagno.exchlist.R
import com.carlosmagno.exchlist.domain.model.Exchange
import com.carlosmagno.exchlist.formatLargeNumber
import com.carlosmagno.exchlist.presentation.ui.components.SetStatusBarColor
import com.carlosmagno.exchlist.presentation.ui.theme.DarkGray
import com.carlosmagno.exchlist.presentation.ui.theme.Dimens
import com.carlosmagno.exchlist.presentation.ui.theme.LightGreen
import com.carlosmagno.exchlist.presentation.viewmodel.FavoriteViewModel
import org.koin.androidx.compose.getViewModel

@SuppressLint("ContextCastToActivity")
@Composable
fun FavoriteScreen(
    onExchangeClick: (Exchange) -> Unit
) {
    val viewModel: FavoriteViewModel = getViewModel()
    val favorites by viewModel.favorites.collectAsState()

    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    SetStatusBarColor(color = DarkGray, darkIcons = false)

    LaunchedEffect(Unit) { viewModel.loadFavorites() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(Dimens.paddingScreen16)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = { backDispatcher?.onBackPressed() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = stringResource(R.string.back_button),
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
            Spacer(modifier = Modifier.width(Dimens.spacingScreen8))
            Text(
                text = stringResource(R.string.favorites_title),
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(Dimens.spacingScreen12))

        if (favorites.isEmpty()) {
            Text(
                text = stringResource(R.string.favorites_empty_message),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(Dimens.spacingScreen8)) {
                items(favorites) { exchange ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(Dimens.spacingScreen12))
                            .clickable { onExchangeClick(exchange) },
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = Dimens.spacingScreen4)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(Dimens.paddingScreen16),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.weight(1f)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_dollars_coin),
                                    contentDescription = stringResource(R.string.default_icon_desc),
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(Dimens.spacingScreen48)
                                        .clip(CircleShape)
                                )
                                Spacer(modifier = Modifier.width(Dimens.spacingScreen12))
                                Column {
                                    Text(
                                        text = exchange.name ?: stringResource(R.string.unknown_exchange_name),
                                        style = MaterialTheme.typography.titleMedium.copy(
                                            fontWeight = FontWeight.Bold,
                                            fontSize = Dimens.fontSizeSubtitle18
                                        ),
                                        modifier = Modifier.padding(bottom = Dimens.paddingScreen4)
                                    )
                                    Text(
                                        text = stringResource(R.string.exchange_id_label) + exchange.id,
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }

                            Column(horizontalAlignment = Alignment.End) {
                                Text(
                                    text = stringResource(R.string.volume_24h_label),
                                    style = MaterialTheme.typography.labelSmall,
                                    modifier = Modifier.padding(bottom = Dimens.paddingScreen4)
                                )
                                Text(
                                    text = formatLargeNumber(exchange.dailyVolumeUsd),
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = LightGreen
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}