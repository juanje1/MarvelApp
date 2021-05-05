package com.marvelapp.extensions

import com.google.gson.Gson
import com.marvelapp.data.*
import java.io.FileNotFoundException
import java.math.BigInteger
import java.net.URL
import java.security.MessageDigest
import java.text.DateFormat
import java.util.*

const val PUBLIC_KEY = "6ed4c80bb0686390bec99fea9b879eaa"
const val PRIVATE_KEY = "93d00a630cf68dd240028ff601acbf972dda6f66"
const val SERVER = "https://gateway.marvel.com"
const val ENDPOINT_CHARACTERS = "/v1/public/characters"
const val URL_CHARACTERS = "${SERVER}${ENDPOINT_CHARACTERS}"
const val URL_CHARACTERS_BY_ID = "${SERVER}${ENDPOINT_CHARACTERS}/"
const val MESSAGE_ERROR = "Internal Error in Marvel App"
const val MAX_RETRIES = 3
const val MESSAGE_MAX_RETRIES = "Maximum number of retries"

fun getTimestamp() = Calendar.getInstance().timeInMillis.toString()

fun getMd5(input:String): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
}

fun getCurrentDate(): String {
    val df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault())
    return df.format(Date())
}

fun executeUrl(url: String): CharacterDataWrapper? {
    val gson = Gson()
    var outputJsonStr: String ?= null

    try { outputJsonStr = URL(url).readText() }
    catch (exception: FileNotFoundException) { }
    finally { return getGson(gson, outputJsonStr) }
}

private fun getGson(gson: Gson, outputJsonStr: String?): CharacterDataWrapper? =
    gson.fromJson(outputJsonStr, CharacterDataWrapper::class.java)

fun getCharacterDataWrapper(numberElements: Int): CharacterDataWrapper {
    val charactersList = ArrayList<Characters>()
    for (i in 1..numberElements) charactersList.add(getCharacters(i))
    val characterDataContainer = CharacterDataContainer(25, 25, charactersList)

    return CharacterDataWrapper(200, "OK", characterDataContainer)
}

private fun getCharacters(id: Int): Characters {
    val image = Image("/img/src/picture$id", "jpg")

    val comicSummary1 = ComicSummary("name1")
    val comicSummary2 = ComicSummary("name2")
    val comicList = ComicList(1, arrayListOf(comicSummary1, comicSummary2))

    val storySummary1 = StorySummary("name3")
    val storySummary2 = StorySummary("name4")
    val storyList = StoryList(2, arrayListOf(storySummary1, storySummary2))

    val eventSummary1 = EventSummary("name5")
    val eventSummary2 = EventSummary("name6")
    val eventList = EventList(3, arrayListOf(eventSummary1, eventSummary2))

    val seriesSummary1 = SeriesSummary("name7")
    val seriesSummary2 = SeriesSummary("name8")
    val seriesList = SeriesList(4, arrayListOf(seriesSummary1, seriesSummary2))

    return Characters(id, "Name of Character", "Description of Character", image, comicList,
        storyList, eventList, seriesList)
}