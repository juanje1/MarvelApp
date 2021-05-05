package com.marvelapp.data

import com.marvelapp.domain.model.*
import com.marvelapp.extensions.getCharacterDataWrapper
import org.junit.Assert.assertEquals
import org.junit.Test

class ServerDataMapperCharactersTest {

    @Test
    fun `ServerDataMapper convert CharacterDataWrapper to CharacterDataWrapperResult`() {
        val serverDataMapperCharacters = ServerDataMapperCharacters()
        val characterDataWrapperResult = serverDataMapperCharacters.convertToDomain(getCharacterDataWrapper(3))

        assertEquals(getCharacterDataWrapperResult(), characterDataWrapperResult)
    }

    private fun getCharacterDataWrapperResult(): CharacterDataWrapperResult {
        val charactersResultList = ArrayList<CharactersResult>()
        for (i in 1..3) charactersResultList.add(getCharactersResult(i))
        val characterDataContainerResult = CharacterDataContainerResult(25, 25, charactersResultList)

        return CharacterDataWrapperResult(200, "OK", characterDataContainerResult)
    }

    private fun getCharactersResult(id: Int): CharactersResult {
        val image = "/img/src/picture${id}.jpg"

        val comicListResult = ComicListResult(1)
        val storyListResult = StoryListResult(2)
        val eventListResult = EventListResult(3)
        val seriesListResult = SeriesListResult(4)

        return CharactersResult(id, "Name of Character", "Description of Character", image,
            comicListResult, storyListResult, eventListResult, seriesListResult)
    }
}