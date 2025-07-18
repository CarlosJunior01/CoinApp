package com.carlosmagno.exchlist.domain.usecase

import com.carlosmagno.exchlist.data.repository.factory.FakeDataFactory
import com.carlosmagno.exchlist.domain.repository.ExchangeRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetExchangesUseCaseTest {

    private lateinit var repository: ExchangeRepository
    private lateinit var useCase: GetExchangesUseCase

    @Before
    fun setUp() {
        repository = mockk()
        useCase = GetExchangesUseCase(repository)
    }

    @Test
    fun `should return list of exchanges from repository`() = runTest {
        val fakeList = listOf(FakeDataFactory.fakeExchange(), FakeDataFactory.fakeExchange())
        coEvery { repository.getExchanges() } returns fakeList

        val result = useCase()

        assertEquals(fakeList, result)
        coVerify(exactly = 1) { repository.getExchanges() }
    }

    @Test
    fun `should throw exception when repository fails`() = runTest {
        coEvery { repository.getExchanges() } throws RuntimeException("Repository error")

        val exception = kotlin.runCatching {
            useCase()
        }.exceptionOrNull()

        assertEquals("Repository error", exception?.message)
        coVerify { repository.getExchanges() }
    }
}