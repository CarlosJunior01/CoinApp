package com.carlosmagno.exchlist.data.repository

import com.carlosmagno.exchlist.data.repository.factory.FakeDataFactory
import com.carlosmagno.exchlist.data.local.dao.FavoriteExchangeDao
import com.carlosmagno.exchlist.data.mapper.toDomain
import com.carlosmagno.exchlist.data.mapper.toEntity
import io.mockk.*
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class FavoriteRepositoryImplTest {

    private lateinit var dao: FavoriteExchangeDao
    private lateinit var repository: FavoriteRepositoryImpl

    @Before
    fun setup() {
        dao = mockk(relaxed = true)
        repository = FavoriteRepositoryImpl(dao)
    }

    @Test
    fun `should return list of favorites from DAO`() = runTest {
        val entityList = FakeDataFactory.fakeEntityList(2)
        coEvery { dao.getAllFavorites() } returns entityList

        val result = repository.getFavorites()

        assertEquals(entityList.map { it.toDomain() }, result)
        coVerify { dao.getAllFavorites() }
    }

    @Test
    fun `should insert favorite into DAO`() = runTest {
        val exchange = FakeDataFactory.fakeExchange()
        coJustRun { dao.insertFavorite(exchange.toEntity()) }

        repository.addFavorite(exchange)

        coVerify { dao.insertFavorite(exchange.toEntity()) }
    }

    @Test
    fun `should remove favorite from DAO`() = runTest {
        val exchange = FakeDataFactory.fakeExchange()
        coJustRun { dao.deleteFavorite(exchange.toEntity()) }

        repository.removeFavorite(exchange)

        coVerify { dao.deleteFavorite(exchange.toEntity()) }
    }

    @Test
    fun `should return true when ID is favorite`() = runTest {
        val id = "binance"
        coEvery { dao.isFavorite(id) } returns true

        val result = repository.isFavorite(id)

        assertTrue(result)
        coVerify { dao.isFavorite(id) }
    }

    @Test
    fun `should return false when ID is not favorite`() = runTest {
        val id = "kraken"
        coEvery { dao.isFavorite(id) } returns false

        val result = repository.isFavorite(id)

        assertFalse(result)
        coVerify { dao.isFavorite(id) }
    }

    @Test
    fun `should throw exception when DAO fails on getFavorites`() = runTest {
        coEvery { dao.getAllFavorites() } throws RuntimeException("Database error")

        try {
            repository.getFavorites()
            fail("Expected exception not thrown")
        } catch (e: RuntimeException) {
            assertEquals("Database error", e.message)
        }
    }

    @Test
    fun `should throw exception when DAO fails on insert`() = runTest {
        val exchange = FakeDataFactory.fakeExchange()
        coEvery { dao.insertFavorite(any()) } throws RuntimeException("Insert failed")

        try {
            repository.addFavorite(exchange)
            fail("Expected exception not thrown")
        } catch (e: RuntimeException) {
            assertEquals("Insert failed", e.message)
        }
    }

    @Test
    fun `should throw exception when DAO fails on delete`() = runTest {
        val exchange = FakeDataFactory.fakeExchange()
        coEvery { dao.deleteFavorite(any()) } throws RuntimeException("Delete failed")

        try {
            repository.removeFavorite(exchange)
            fail("Expected exception not thrown")
        } catch (e: RuntimeException) {
            assertEquals("Delete failed", e.message)
        }
    }
}
