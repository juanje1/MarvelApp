package com.marvelapp.domain.datasource

import com.marvelapp.data.MarvelServer
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class MarvelProviderTest {

    private val source = mock(MarvelServer::class.java)
    private val provider = MarvelProvider(source)

    @Test
    fun `data source is called when requestCharacters is executed`() {
        runBlocking { provider.requestCharacters("name", 25) }
        verify(source).requestCharacters("name", 25)
    }

    @Test
    fun `data source is called when requestCharacterById is executed`() {
        runBlocking { provider.requestCharacterById(1) }
        verify(source).requestCharacterById(1)
    }
}