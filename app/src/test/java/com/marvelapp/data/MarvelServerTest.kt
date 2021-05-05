package com.marvelapp.data

import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito

class MarvelServerTest {

    private val dataMapperCharacters = Mockito.mock(ServerDataMapperCharacters::class.java)
    private val dataMapperCharacterById = Mockito.mock(ServerDataMapperCharacterById::class.java)
    private val marvelServer = MarvelServer(dataMapperCharacters, dataMapperCharacterById)

    @Test
    fun `dataMapperCharacters is called when result is not null`() {
        val result = CharactersRequest("name", 25).execute()

        runBlocking { marvelServer.requestCharacters("name", 25) }
        if (result != null) Mockito.verify(dataMapperCharacters).convertToDomain(result)
    }

    @Test
    fun `dataMapperCharacterById is called when result is not null`() {
        val resultCharacters = CharactersRequest("name", 25).execute()

        if (resultCharacters != null) {
            val id = resultCharacters.data[0].id
            val result = CharacterByIdRequest(id).execute()

            runBlocking { marvelServer.requestCharacterById(id) }
            if (result != null) Mockito.verify(dataMapperCharacterById).convertToDomain(result)
        }
    }
}