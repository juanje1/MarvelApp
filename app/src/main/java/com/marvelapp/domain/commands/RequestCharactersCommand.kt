package com.marvelapp.domain.commands

import com.marvelapp.domain.datasource.MarvelProvider
import com.marvelapp.domain.model.CharacterDataWrapperResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RequestCharactersCommand(private val orderBy: String, private val limit: Int,
                               private val marvelProvider: MarvelProvider = MarvelProvider()):
    Command<CharacterDataWrapperResult?> {

    override suspend fun execute() = withContext(Dispatchers.IO) {
        marvelProvider.requestCharacters(orderBy, limit)
    }
}