package com.carlosmagno.exchlist.presentation.viewmodel

import com.carlosmagno.exchlist.domain.model.Exchange
import com.carlosmagno.exchlist.domain.repository.FavoriteRepository
import com.carlosmagno.exchlist.presentation.viewmodel.helper.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FavoriteViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private lateinit var repository: FavoriteRepository
    private lateinit var viewModel: FavoriteViewModel

    private val sampleExchange = Exchange(
        id = "1",
        name = "Test Exchange",
        dailyVolumeUsd = 1000.0,
        volume1HrsUsd = 100.0,
        volume1MonthUsd = 30000.0,
        symbolsCount = 10,
        quoteStart = null,
        quoteEnd = null,
        orderbookStart = null,
        orderbookEnd = null,
        tradeStart = null,
        tradeEnd = null,
        website = null,
        rank = 1,
        integrationStatus = null
    )

    @Before
    fun setup() {
        repository = mockk()
    }

    @Test
    fun `init should load favorites from repository`() = runTest {
        coEvery { repository.getFavorites() } returns listOf(sampleExchange)

        viewModel = FavoriteViewModel(repository)

        advanceUntilIdle()

        assertEquals(listOf(sampleExchange), viewModel.favorites.value)
    }

    @Test
    fun `loadFavorites should update favorites flow`() = runTest {
        coEvery { repository.getFavorites() } returns listOf(sampleExchange)
        viewModel = FavoriteViewModel(repository)

        viewModel.loadFavorites()
        advanceUntilIdle()

        assertEquals(listOf(sampleExchange), viewModel.favorites.value)
    }

    @Test
    fun `toggleFavorite should add favorite when not favorite yet`() = runTest {
        coEvery { repository.isFavorite(sampleExchange.id) } returns false
        coEvery { repository.addFavorite(sampleExchange) } returns Unit
        coEvery { repository.getFavorites() } returns listOf(sampleExchange)

        viewModel = FavoriteViewModel(repository)

        viewModel.toggleFavorite(sampleExchange)
        advanceUntilIdle()

        coVerify(exactly = 1) { repository.addFavorite(sampleExchange) }
        coVerify(exactly = 0) { repository.removeFavorite(sampleExchange) }

        assertTrue(viewModel.favorites.value.contains(sampleExchange))
    }

    @Test
    fun `toggleFavorite should remove favorite when already favorite`() = runTest {
        coEvery { repository.isFavorite(sampleExchange.id!!) } returns true
        coEvery { repository.removeFavorite(sampleExchange) } returns Unit
        coEvery { repository.getFavorites() } returns emptyList()

        viewModel = FavoriteViewModel(repository)

        viewModel.toggleFavorite(sampleExchange)
        advanceUntilIdle()

        coVerify(exactly = 1) { repository.removeFavorite(sampleExchange) }
        coVerify(exactly = 0) { repository.addFavorite(sampleExchange) }

        assertFalse(viewModel.favorites.value.contains(sampleExchange))
    }

    @Test
    fun `isFavorite returns true if exchange is in favorites`() = runTest {
        coEvery { repository.getFavorites() } returns listOf(sampleExchange)
        viewModel = FavoriteViewModel(repository)

        advanceUntilIdle()

        assertTrue(viewModel.isFavorite(sampleExchange.id))
        assertFalse(viewModel.isFavorite("non_existing_id"))
        assertFalse(viewModel.isFavorite(null))
    }
}

