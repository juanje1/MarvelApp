package com.marvelapp.domain.commands

import com.marvelapp.domain.datasource.MarvelProvider
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class RequestCharactersCommandTest {

    @Test
    fun `provider is called when command is executed`() {
        val marvelProvider = mock(MarvelProvider::class.java)
        val command = RequestCharactersCommand("name", 25, marvelProvider)

        runBlocking { command.execute() }

        verify(marvelProvider).requestCharacters("name", 25)
    }
}