package com.carlosmagno.exchlist.presentation.ui.screen

import android.annotation.SuppressLint
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.carlosmagno.exchlist.R
import com.carlosmagno.exchlist.domain.model.Exchange
import com.carlosmagno.exchlist.formatLargeNumber
import com.carlosmagno.exchlist.navigation.Routes
import com.carlosmagno.exchlist.presentation.ui.components.SetStatusBarColor
import com.carlosmagno.exchlist.presentation.ui.theme.DarkGray
import com.carlosmagno.exchlist.presentation.ui.theme.Dimens
import com.carlosmagno.exchlist.presentation.ui.theme.LightBlue
import com.carlosmagno.exchlist.presentation.ui.theme.LightGreen
import com.carlosmagno.exchlist.presentation.viewmodel.ExchangeViewModel
import org.koin.androidx.compose.getViewModel

@SuppressLint("ContextCastToActivity")
@Composable
fun ExchangeListScreen(
    navController: NavController,
    onExchangeClick: (Exchange) -> Unit,
    viewModel: ExchangeViewModel = getViewModel()
) {
    val exchanges = viewModel.state
    val error = viewModel.error

    val cryptoAssetsText = stringResource(id = R.string.crypto_assets)
    val iconCryptoAssetsDesc = stringResource(id = R.string.icon_crypto_assets_desc)
    val favoritesText = stringResource(id = R.string.favorites)
    val goToFavoritesDesc = stringResource(id = R.string.go_to_favorites_desc)
    val errorPrefix = stringResource(id = R.string.error_prefix)
    val defaultExchangeName = stringResource(id = R.string.default_exchange_name)
    val volume24hText = stringResource(id = R.string.volume_24h)
    val defaultIconDesc = stringResource(id = R.string.default_icon_desc)

    SetStatusBarColor(color = DarkGray, darkIcons = false)
    LaunchedEffect(Unit) { viewModel.loadExchanges() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(Dimens.paddingScreen16)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = cryptoAssetsText,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = Dimens.fontSizeTitle24
                    ),
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.width(Dimens.spacingScreen8))
                Icon(
                    painter = painterResource(id = R.drawable.ic_dollars_coin),
                    contentDescription = iconCryptoAssetsDesc,
                    tint = Color.White,
                    modifier = Modifier.size(Dimens.spacingScreen24)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable { navController.navigate(Routes.FAVORITES) }
                    .padding(Dimens.paddingScreen8)
            ) {
                Text(
                    text = favoritesText,
                    color = LightBlue,
                    fontWeight = FontWeight.Medium,
                    fontSize = Dimens.fontSizeBody16
                )
                Spacer(modifier = Modifier.width(Dimens.spacingScreen4))
                Icon(
                    painter = painterResource(id = R.drawable.ic_favorite),
                    contentDescription = goToFavoritesDesc,
                    tint = LightBlue,
                    modifier = Modifier.size(Dimens.spacingScreen16)
                )
            }
        }

        Spacer(modifier = Modifier.height(Dimens.spacingScreen12))

        if (error != null) {
            Text(
                text = String.format(errorPrefix, error),
                color = MaterialTheme.colorScheme.error
            )
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(Dimens.spacingScreen8)) {
                items(exchanges) { exchange ->
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
                                    contentDescription = defaultIconDesc,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(Dimens.spacingScreen48)
                                        .clip(CircleShape)
                                )
                                Spacer(modifier = Modifier.width(Dimens.spacingScreen12))
                                Column {
                                    Text(
                                        text = exchange.name ?: defaultExchangeName,
                                        style = MaterialTheme.typography.titleMedium.copy(
                                            fontWeight = FontWeight.Bold,
                                            fontSize = Dimens.fontSizeSubtitle18
                                        ),
                                        modifier = Modifier.padding(bottom = Dimens.paddingScreen4)
                                    )
                                    val exchangeIdText = stringResource(id = R.string.exchange_id, exchange.id)
                                    Text(
                                        text = exchangeIdText,
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }

                            Column(horizontalAlignment = Alignment.End) {
                                Text(
                                    text = volume24hText,
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