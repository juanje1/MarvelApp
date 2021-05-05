package com.marvelapp.domain.commands

import com.marvelapp.domain.datasource.MarvelProvider
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class RequestCharacterByIdCommandTest {

    @Test
    fun `provider is called when command is executed`() {
        val marvelProvider = mock(MarvelProvider::class.java)
        val command = RequestCharacterByIdCommand(1, marvelProvider)

        runBlocking { command.execute() }

        verify(marvelProvider).requestCharacterById(1)
    }
}