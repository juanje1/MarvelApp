package com.marvelapp.domain.datasource

import com.marvelapp.domain.model.CharacterDataWrapperByIdResult
import com.marvelapp.domain.model.CharacterDataWrapperResult

interface MarvelDataSource {

    fun requestCharacters(orderBy: String, limit: Int): CharacterDataWrapperResult?

    fun requestCharacterById(characterId: Int): CharacterDataWrapperByIdResult?

}