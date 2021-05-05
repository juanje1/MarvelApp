package com.marvelapp.domain.datasource

import com.marvelapp.data.MarvelServer
import com.marvelapp.domain.model.CharacterDataWrapperByIdResult
import com.marvelapp.domain.model.CharacterDataWrapperResult

class MarvelProvider(private val source: MarvelDataSource = SOURCE) {

    companion object {
        val SOURCE by lazy { MarvelServer() }
    }

    fun requestCharacters(orderBy: String, limit: Int): CharacterDataWrapperResult? =
        source.requestCharacters(orderBy, limit)

    fun requestCharacterById(characterId: Int): CharacterDataWrapperByIdResult? =
        source.requestCharacterById(characterId)
}