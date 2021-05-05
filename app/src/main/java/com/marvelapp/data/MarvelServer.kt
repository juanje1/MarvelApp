package com.marvelapp.data

import com.marvelapp.domain.datasource.MarvelDataSource
import com.marvelapp.domain.model.CharacterDataWrapperByIdResult
import com.marvelapp.domain.model.CharacterDataWrapperResult

class MarvelServer(private val dataMapperCharacters: ServerDataMapperCharacters = ServerDataMapperCharacters(),
                   private val dataMapperCharacterById: ServerDataMapperCharacterById = ServerDataMapperCharacterById())
    : MarvelDataSource {

    override fun requestCharacters(orderBy: String, limit: Int): CharacterDataWrapperResult? {
        val result = CharactersRequest(orderBy, limit).execute()
        return result?.let { dataMapperCharacters.convertToDomain(it) }
    }

    override fun requestCharacterById(characterId: Int): CharacterDataWrapperByIdResult? {
        val result = CharacterByIdRequest(characterId).execute()
        return result?.let { dataMapperCharacterById.convertToDomain(it) }
    }
}