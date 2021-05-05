package com.marvelapp.data

import com.marvelapp.domain.model.*

class ServerDataMapperCharacters {

    fun convertToDomain(characters: CharacterDataWrapper) = with(characters) {
        CharacterDataWrapperResult(code, status, convertCharacterDataContainerToDomain(data))
    }

    private fun convertCharacterDataContainerToDomain(data: CharacterDataContainer) = with(data) {
        CharacterDataContainerResult(total, count, convertCharactersListToDomain(results))
    }

    private fun convertCharactersListToDomain(list: List<Characters>) =
        list.map { convertCharacterItemToDomain(it.copy()) }

    private fun convertCharacterItemToDomain(character: Characters) = with(character) {
        CharactersResult(id, name, description, generateIconUrl(thumbnail), convertComicsToDomain(comics),
            convertStoriesToDomain(stories), convertEventsToDomain(events), convertSeriesToDomain(series))
    }

    private fun generateIconUrl(thumbnail: Image) = with(thumbnail) {
        "${path}.${extension}".replace("http","https")
    }

    private fun convertComicsToDomain(comics: ComicList) = with(comics) {
        ComicListResult(available)
    }

    private fun convertStoriesToDomain(stories: StoryList) = with(stories) {
        StoryListResult(available)
    }

    private fun convertEventsToDomain(events: EventList) = with(events) {
        EventListResult(available)
    }

    private fun convertSeriesToDomain(series: SeriesList) = with(series) {
        SeriesListResult(available)
    }
}