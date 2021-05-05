package com.marvelapp.domain.model

data class CharacterDataWrapperByIdResult(val code: Int, val status: String, val data: CharacterDataContainerByIdResult)

data class CharacterDataContainerByIdResult(val total: Int, val count: Int, val results: List<CharactersByIdResult>) {
    val size: Int
        get() = results.size
    operator fun get(position: Int) = results[position]
}

data class CharactersByIdResult(val id: Int, val name: String, val description: String, val thumbnail: String,
                                val comics: ComicByIdListResult, val stories: StoryByIdListResult,
                                val events: EventByIdListResult, val series: SeriesByIdListResult)

data class ComicByIdListResult(val items: List<ComicByIdSummary>) {
    val size: Int
        get() = items.size
    operator fun get(position: Int) = items[position]
}

data class ComicByIdSummary(val name: String)

data class StoryByIdListResult(val items: List<StoryByIdSummary>) {
    val size: Int
        get() = items.size
    operator fun get(position: Int) = items[position]
}

data class StoryByIdSummary(val name: String)

data class EventByIdListResult(val items: List<EventByIdSummary>) {
    val size: Int
        get() = items.size
    operator fun get(position: Int) = items[position]
}

data class EventByIdSummary(val name: String)

data class SeriesByIdListResult(val items: List<SeriesByIdSummary>) {
    val size: Int
        get() = items.size
    operator fun get(position: Int) = items[position]
}

data class SeriesByIdSummary(val name: String)