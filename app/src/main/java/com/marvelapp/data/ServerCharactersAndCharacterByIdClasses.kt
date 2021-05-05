package com.marvelapp.data

data class CharacterDataWrapper(val code: Int, val status: String, val data: CharacterDataContainer)

data class CharacterDataContainer(val total: Int, val count: Int, val results: List<Characters>) {
    val size: Int
        get() = results.size
    operator fun get(position: Int) = results[position]
}

data class Characters(val id: Int, val name: String, val description: String, val thumbnail: Image,
                      val comics: ComicList, val stories: StoryList, val events: EventList, val series: SeriesList)

data class Image(val path: String, val extension: String)

data class ComicList(val available: Int, val items: List<ComicSummary>) {
    val size: Int
        get() = items.size
    operator fun get(position: Int) = items[position]
}

data class ComicSummary(val name: String)

data class StoryList(val available: Int, val items: List<StorySummary>) {
    val size: Int
        get() = items.size
    operator fun get(position: Int) = items[position]
}

data class StorySummary(val name: String)

data class EventList(val available: Int, val items: List<EventSummary>) {
    val size: Int
        get() = items.size
    operator fun get(position: Int) = items[position]
}

data class EventSummary(val name: String)

data class SeriesList(val available: Int, val items: List<SeriesSummary>) {
    val size: Int
        get() = items.size
    operator fun get(position: Int) = items[position]
}

data class SeriesSummary(val name: String)