package com.marvelapp.ui.activities

import android.graphics.Color
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.google.android.material.snackbar.Snackbar
import com.marvelapp.R
import com.marvelapp.domain.commands.RequestCharacterByIdCommand
import com.marvelapp.domain.model.CharactersByIdResult
import com.marvelapp.extensions.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.launch
import org.jetbrains.anko.find
import org.jetbrains.anko.toast
import java.lang.StringBuilder

class DetailActivity : CoroutineScopeActivity(), ToolbarManager {

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    companion object {
        const val CHARACTER_ID = "DetailActivity:characterId"
        var numberRetries = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        initToolbar()
        enableHomeAsUp { onBackPressed() }
        loadCharacterById()
    }

    private fun loadCharacterById() = launch {
        val characterId = intent.getIntExtra(CHARACTER_ID, -1)
        val result = RequestCharacterByIdCommand(characterId).execute()

        if (result != null) {
            bindCharacterById(result.data[0])
            numberRetries = 0
        } else { if (getRetry()) showErrorAndRetry(findViewById(R.id.scrollView)) else toast(MESSAGE_MAX_RETRIES) }
    }

    private fun showErrorAndRetry(view: View) {
        Snackbar.make(view, MESSAGE_ERROR, Snackbar.LENGTH_LONG)
            .setAction("Retry"){ loadCharacterById() }
            .setActionTextColor(Color.RED)
            .show()
    }

    private fun getRetry(): Boolean {
        numberRetries += 1
        return numberRetries <= MAX_RETRIES
    }

    private fun bindCharacterById(character: CharactersByIdResult) = with(character) {
        toolbarTitle = name
        Picasso.with(characterIcon.ctx).load(thumbnail).into(characterIcon)
        characterName.text = name
        characterDescription.text = description

        characterComics.text = Html.fromHtml(characterComics.ctx.getString(R.string.characterComics),1)
        val comicsStringBuilder = StringBuilder()
        comics.items.forEach{ comicsStringBuilder.append("${it.name}\n") }
        comicsList.text = comicsStringBuilder

        characterStories.text = Html.fromHtml(characterStories.ctx.getString(R.string.characterStories),1)
        val storiesStringBuilder = StringBuilder()
        stories.items.forEach{ storiesStringBuilder.append("${it.name}\n") }
        storiesList.text = storiesStringBuilder

        characterEvents.text = Html.fromHtml(characterEvents.ctx.getString(R.string.characterEvents),1)
        val eventsStringBuilder = StringBuilder()
        events.items.forEach{ eventsStringBuilder.append("${it.name}\n") }
        eventsList.text = eventsStringBuilder

        characterSeries.text = Html.fromHtml(characterSeries.ctx.getString(R.string.characterSeries),1)
        val seriesStringBuilder = StringBuilder()
        series.items.forEach{ seriesStringBuilder.append("${it.name}\n") }
        seriesList.text = seriesStringBuilder
    }
}