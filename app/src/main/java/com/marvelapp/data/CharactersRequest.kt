package com.marvelapp.data

import android.util.Log
import com.marvelapp.extensions.*

class CharactersRequest(private val orderBy: String, private val limit: Int) {

    fun execute(): CharacterDataWrapper? {
        val timestamp = getTimestamp()
        val md5 = getMd5("${timestamp}${PRIVATE_KEY}${PUBLIC_KEY}")
        val url = "${URL_CHARACTERS}?ts=${timestamp}&orderBy=${orderBy}&limit=${limit}&apikey=${PUBLIC_KEY}&hash=${md5}"
        Log.d("Url", url)
        return executeUrl(url)
    }
}