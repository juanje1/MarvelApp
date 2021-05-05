package com.marvelapp.data

import com.marvelapp.domain.model.*

class ServerDataMapperCharacterById {

    fun convertToDomain(characters: CharacterDataWrapper) = with(characters) {
        CharacterDataWrapperByIdResult(code, status, convertCharacterDataContainerByIdToDomain(data))
    }

    private fun convertCharacterDataContainerByIdToDomain(data: CharacterDataContainer) = with(data) {
        CharacterDataContainerByIdResult(total, count, convertCharactersByIdListToDomain(results))
    }

    private fun convertCharactersByIdListToDomain(list: List<Characters>) =
        list.map { convertCharacterByIdItemToDomain(it.copy()) }

    private fun convertCharacterByIdItemToDomain(character: Characters) = with(character) {
        CharactersByIdResult(
            id, name, description, generateIconUrl(thumbnail), convertComicsByIdToDomain(comics),
            convertStoriesByIdToDomain(stories), convertEventsByIdToDomain(events), convertSeriesByIdToDomain(series)
        )
    }

    private fun generateIconUrl(thumbnail: Image) = with(thumbnail) {
        "${path}.${extension}".replace("http", "https")
    }

    private fun convertComicsByIdToDomain(comics: ComicList) = with(comics) {
        ComicByIdListResult(convertComicsByIdListToDomain(items))
    }

    private fun convertComicsByIdListToDomain(list: List<ComicSummary>) =
        list.map { convertComicByIdItemToDomain(it.copy()) }

    private fun convertComicByIdItemToDomain(comicSummary: ComicSummary) = with(comicSummary) {
        ComicByIdSummary(name)
    }

    private fun convertStoriesByIdToDomain(stories: StoryList) = with(stories) {
        StoryByIdListResult(convertStoriesByIdListToDomain(items))
    }

    private fun convertStoriesByIdListToDomain(list: List<StorySummary>) =
        list.map { convertStoryByIdItemToDomain(it.copy()) }

    private fun convertStoryByIdItemToDomain(storySummary: StorySummary) = with(storySummary) {
        StoryByIdSummary(name)
    }

    private fun convertEventsByIdToDomain(events: EventList) = with(events) {
        EventByIdListResult(convertEventsByIdListToDomain(items))
    }

    private fun convertEventsByIdListToDomain(list: List<EventSummary>) =
        list.map { convertEventByIdItemToDomain(it.copy()) }

    private fun convertEventByIdItemToDomain(eventSummary: EventSummary) = with(eventSummary) {
        EventByIdSummary(name)
    }

    private fun convertSeriesByIdToDomain(series: SeriesList) = with(series) {
        SeriesByIdListResult(convertSeriesByIdListToDomain(items))
    }

    private fun convertSeriesByIdListToDomain(list: List<SeriesSummary>) =
        list.map { convertSeriesByIdItemToDomain(it.copy()) }

    private fun convertSeriesByIdItemToDomain(seriesSummary: SeriesSummary) = with(seriesSummary) {
        SeriesByIdSummary(name)
    }
}