package com.marvelapp.data

import com.marvelapp.domain.model.*
import com.marvelapp.extensions.getCharacterDataWrapper
import org.junit.Assert
import org.junit.Test

class ServerDataMapperCharacterByIdTest {

    @Test
    fun `ServerDataMapper convert CharacterDataWrapper to CharacterDataWrapperByIdResult`() {
        val serverDataMapperCharacterById = ServerDataMapperCharacterById()
        val characterDataWrapperByIdResult = serverDataMapperCharacterById.convertToDomain(getCharacterDataWrapper(1))

        Assert.assertEquals(getCharacterDataWrapperByIdResult(), characterDataWrapperByIdResult)
    }

    private fun getCharacterDataWrapperByIdResult(): CharacterDataWrapperByIdResult {
        val charactersByIdResultList = ArrayList<CharactersByIdResult>()
        charactersByIdResultList.add(getCharactersByIdResult(1))
        val characterDataContainerByIdResult = CharacterDataContainerByIdResult(25, 25,
            charactersByIdResultList)

        return CharacterDataWrapperByIdResult(200, "OK", characterDataContainerByIdResult)
    }

    private fun getCharactersByIdResult(id: Int): CharactersByIdResult {
        val image = "/img/src/picture${id}.jpg"

        val comicByIdSummary1 = ComicByIdSummary("name1")
        val comicByIdSummary2 = ComicByIdSummary("name2")
        val comicByIdListResult = ComicByIdListResult(arrayListOf(comicByIdSummary1, comicByIdSummary2))

        val storyByIdSummary1 = StoryByIdSummary("name3")
        val storyByIdSummary2 = StoryByIdSummary("name4")
        val storyByIdListResult = StoryByIdListResult(arrayListOf(storyByIdSummary1, storyByIdSummary2))

        val eventByIdSummary1 = EventByIdSummary("name5")
        val eventByIdSummary2 = EventByIdSummary("name6")
        val eventByIdListResult = EventByIdListResult(arrayListOf(eventByIdSummary1, eventByIdSummary2))

        val seriesByIdSummary1 = SeriesByIdSummary("name7")
        val seriesByIdSummary2 = SeriesByIdSummary("name8")
        val seriesByIdListResult = SeriesByIdListResult(arrayListOf(seriesByIdSummary1, seriesByIdSummary2))

        return CharactersByIdResult(id, "Name of Character", "Description of Character", image,
            comicByIdListResult, storyByIdListResult, eventByIdListResult, seriesByIdListResult)
    }
}