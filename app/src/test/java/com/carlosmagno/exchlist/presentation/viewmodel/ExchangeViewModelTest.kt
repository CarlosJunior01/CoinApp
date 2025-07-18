package com.carlosmagno.exchlist.presentation.viewmodel

import com.carlosmagno.exchlist.data.repository.factory.FakeDataFactory
import com.carlosmagno.exchlist.domain.usecase.GetExchangesUseCase
import com.carlosmagno.exchlist.presentation.viewmodel.helper.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ExchangeViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private lateinit var useCase: GetExchangesUseCase
    private lateinit var viewModel: ExchangeViewModel

    @Before
    fun setup() {
        useCase = mockk()
        viewModel = ExchangeViewModel(useCase)
    }

    @Test
    fun `initial state should be empty list and null error`() {
        assertTrue(viewModel.state.isEmpty())
        assertNull(viewModel.error)
    }

    @Test
    fun `should load exchanges successfully and update state`() = runTest {
        val fakeList = listOf(FakeDataFactory.fakeExchange(), FakeDataFactory.fakeExchange())
        coEvery { useCase.invoke() } returns fakeList

        viewModel.loadExchanges()
        advanceUntilIdle()

        assertEquals(fakeList, viewModel.state)
        assertNull(viewModel.error)
    }

    @Test
    fun `should update error when use case throws exception`() = runTest {
        coEvery { useCase.invoke() } throws RuntimeException("Erro de rede")

        viewModel.loadExchanges()
        advanceUntilIdle()

        assertTrue(viewModel.state.isEmpty())
        assertEquals("Erro de rede", viewModel.error)
    }

    @Test
    fun `should use generic error message if exception has no message`() = runTest {
        coEvery { useCase.invoke() } throws Exception()

        viewModel.loadExchanges()
        advanceUntilIdle()

        assertTrue(viewModel.state.isEmpty())
        assertEquals("Erro desconhecido: tente novamente!", viewModel.error)
    }
}