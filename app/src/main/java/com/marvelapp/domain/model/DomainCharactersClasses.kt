package com.marvelapp.domain.model

data class CharacterDataWrapperResult(val code: Int, val status: String, val data: CharacterDataContainerResult)

data class CharacterDataContainerResult(val total: Int, val count: Int, val results: List<CharactersResult>) {
    val size: Int
        get() = results.size
    operator fun get(position: Int) = results[position]
}

data class CharactersResult(val id: Int, val name: String, val description: String, val thumbnail: String,
                            val comics: ComicListResult, val stories: StoryListResult, val events: EventListResult,
                            val series: SeriesListResult)

data class ComicListResult(val available: Int)

data class StoryListResult(val available: Int)

data class EventListResult(val available: Int)

data class SeriesListResult(val available: Int)