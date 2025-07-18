package com.carlosmagno.exchlist.data.repository

import com.carlosmagno.exchlist.data.mapper.toDomain
import com.carlosmagno.exchlist.data.remote.CoinApiService
import com.carlosmagno.exchlist.data.repository.factory.ExchangeFactory
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ExchangeRepositoryImplTest {

    private lateinit var apiService: CoinApiService
    private lateinit var repository: ExchangeRepositoryImpl

    @Before
    fun setUp() {
        apiService = mockk()
        repository = ExchangeRepositoryImpl(apiService)
    }

    @Test
    fun `should fetch exchanges from API when cache is empty`() = runTest {
        val exchangeDtoList = ExchangeFactory.fakeExchangeDtoList(2)
        coEvery { apiService.getExchanges() } returns exchangeDtoList

        val result = repository.getExchanges()

        assertEquals(exchangeDtoList.map { it.toDomain() }, result)
        coVerify(exactly = 1) { apiService.getExchanges() }
    }

    @Test
    fun `should return cached exchanges if already fetched`() = runTest {
        val exchangeDtoList = ExchangeFactory.fakeExchangeDtoList(1)
        coEvery { apiService.getExchanges() } returns exchangeDtoList

        repository.getExchanges()
        val result = repository.getExchanges()

        assertEquals(exchangeDtoList.map { it.toDomain() }, result)
        coVerify(exactly = 1) { apiService.getExchanges() }
    }

    @Test
    fun `should return correct exchange by ID`() = runTest {
        val exchangeDtoList = ExchangeFactory.fakeExchangeDtoList(3)
        val expected = exchangeDtoList[1].toDomain()

        coEvery { apiService.getExchanges() } returns exchangeDtoList

        repository.getExchanges()
        val result = repository.getExchangeById(expected.id)

        assertEquals(expected, result)
    }

    @Test
    fun `should return null when exchange ID not found`() = runTest {
        val exchangeDtoList = ExchangeFactory.fakeExchangeDtoList(2)
        coEvery { apiService.getExchanges() } returns exchangeDtoList

        repository.getExchanges()
        val result = repository.getExchangeById("non_existing_id")

        assertEquals(null, result)
    }

    @Test(expected = RuntimeException::class)
    fun `should throw exception when API call fails`() = runTest {
        coEvery { apiService.getExchanges() } throws RuntimeException("API error")
        repository.getExchanges()
    }

    @Test
    fun `should handle exception when API call fails`() = runTest {
        coEvery { apiService.getExchanges() } throws RuntimeException("API error")

        try {
            repository.getExchanges()
            assert(false)
        } catch (e: RuntimeException) {
            assertEquals("API error", e.message)
        }
    }
}
