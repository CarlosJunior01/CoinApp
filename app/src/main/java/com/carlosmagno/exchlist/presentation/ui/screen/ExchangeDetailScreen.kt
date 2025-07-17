package com.carlosmagno.exchlist.presentation.ui.screen

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.carlosmagno.exchlist.R
import com.carlosmagno.exchlist.domain.model.Exchange
import com.carlosmagno.exchlist.presentation.ui.components.RankIcons
import com.carlosmagno.exchlist.presentation.ui.components.SectionTitle
import com.carlosmagno.exchlist.presentation.ui.components.VolumeItem
import com.carlosmagno.exchlist.presentation.ui.theme.DarkGray
import com.carlosmagno.exchlist.presentation.ui.theme.Dimens
import com.carlosmagno.exchlist.presentation.ui.theme.LightBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExchangeDetailScreen(
    exchange: Exchange,
    isFavorite: Boolean,
    onToggleFavorite: (Exchange) -> Unit
) {
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    var isFavorited by remember { mutableStateOf(isFavorite) }
    val context = LocalContext.current

    LaunchedEffect(isFavorite) {
        isFavorited = isFavorite
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = exchange.name ?: stringResource(R.string.exchange_details_title),
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { backDispatcher?.onBackPressed() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = stringResource(R.string.back_button),
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        isFavorited = !isFavorited
                        onToggleFavorite(exchange)
                    }) {
                        Icon(
                            imageVector = if (isFavorited) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = if (isFavorited)
                                stringResource(R.string.unfavorite)
                            else
                                stringResource(R.string.favorite),
                            tint = if (isFavorited) Color.Red else Color.Gray
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
                .padding(Dimens.paddingScreen16)
        ) {
            Card(
                shape = RoundedCornerShape(Dimens.spacingScreen12),
                colors = CardDefaults.cardColors(containerColor = DarkGray),
                elevation = CardDefaults.cardElevation(Dimens.spacingScreen16),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(modifier = Modifier.padding(all = Dimens.paddingScreen16)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = Dimens.paddingScreen8),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        exchange.rank?.let { RankIcons(rank = it) }
                        Text(
                            text = stringResource(R.string.exchange_id, exchange.id),
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(Dimens.spacingScreen4))

                    exchange.integrationStatus?.let {
                        Text(
                            text = stringResource(R.string.status_label, it),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = LightBlue
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(Dimens.spacingScreen24))

            SectionTitle(
                title = stringResource(R.string.section_title_volume),
                icon = painterResource(id = R.drawable.ic_moving)
            )

            Spacer(modifier = Modifier.height(Dimens.spacingScreen8))

            VolumeItem(stringResource(R.string.last_1_hour), exchange.volume1HrsUsd)
            VolumeItem(stringResource(R.string.last_24_hours), exchange.dailyVolumeUsd)
            VolumeItem(stringResource(R.string.last_30_days), exchange.volume1MonthUsd)

            Spacer(modifier = Modifier.height(Dimens.spacingScreen24))

            exchange.website?.let { websiteUrl ->
                Card(
                    shape = RoundedCornerShape(Dimens.spacingScreen12),
                    colors = CardDefaults.cardColors(containerColor = DarkGray),
                    elevation = CardDefaults.cardElevation(Dimens.spacingScreen4),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            val validUrl = if (websiteUrl.startsWith("http", ignoreCase = true))
                                websiteUrl
                            else
                                "https://$websiteUrl"

                            try {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(validUrl))
                                val chooser = Intent.createChooser(intent, context.getString(R.string.open_with))
                                context.startActivity(chooser)
                            } catch (e: ActivityNotFoundException) {
                                Toast.makeText(
                                    context,
                                    context.getString(R.string.no_app_found),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(Dimens.paddingScreen16),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = exchange.name ?: stringResource(R.string.exchange_default_name),
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Spacer(Modifier.height(Dimens.spacingScreen8))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_dollars_coin),
                                    contentDescription = stringResource(R.string.exchange_icon_desc),
                                    modifier = Modifier.size(Dimens.spacingScreen24)
                                )
                                Spacer(Modifier.width(Dimens.spacingScreen8))
                                Text(
                                    text = websiteUrl,
                                    color = MaterialTheme.colorScheme.primary,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }

                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_action),
                            contentDescription = stringResource(R.string.ic_arrow_action),
                            tint = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.size(Dimens.spacingScreen24)
                        )
                    }
                }
            }
        }
    }
}