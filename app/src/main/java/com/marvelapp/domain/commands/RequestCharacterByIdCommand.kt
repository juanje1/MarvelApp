package com.marvelapp.domain.commands

import com.marvelapp.domain.datasource.MarvelProvider
import com.marvelapp.domain.model.CharacterDataWrapperByIdResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RequestCharacterByIdCommand(private val characterId: Int,
                                  private val marvelProvider: MarvelProvider = MarvelProvider()):
    Command<CharacterDataWrapperByIdResult?> {

    override suspend fun execute() = withContext(Dispatchers.IO) {
        marvelProvider.requestCharacterById(characterId)
    }
}