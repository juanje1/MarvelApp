package com.marvelapp.data

import android.util.Log
import com.marvelapp.extensions.*

class CharacterByIdRequest(private val characterId: Int) {

    fun execute(): CharacterDataWrapper? {
        val timestamp = getTimestamp()
        val md5 = getMd5("${timestamp}$PRIVATE_KEY${PUBLIC_KEY}")
        val url = "${URL_CHARACTERS_BY_ID}${characterId}?ts=${timestamp}&apikey=${PUBLIC_KEY}&hash=${md5}"
        Log.d("Url", url)
        return executeUrl(url)
    }
}