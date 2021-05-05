package com.marvelapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marvelapp.R
import com.marvelapp.domain.model.CharacterDataWrapperResult
import com.marvelapp.domain.model.CharactersResult
import com.marvelapp.extensions.ctx
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_character.*

class CharactersListAdapter(private val characters: CharacterDataWrapperResult,
                            private val itemClick: (CharactersResult) -> Unit):
    RecyclerView.Adapter<CharactersListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.ctx).inflate(R.layout.item_character, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindCharacter(characters.data[position])
    }

    override fun getItemCount() = characters.data.size

    class ViewHolder(override val containerView: View, private val itemClick: (CharactersResult) -> Unit)
        : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindCharacter(character: CharactersResult) {
            with(character) {
                Picasso.with(itemView.ctx).load(thumbnail).into(icon)
                characterText.text = name
                comicsNumberText.text = itemView.ctx.getString(R.string.comicsNumber, comics.available.toString())
                storiesNumberText.text = itemView.ctx.getString(R.string.storiesNumber, stories.available.toString())
                eventsNumberText.text = itemView.ctx.getString(R.string.eventsNumber, events.available.toString())
                seriesNumberText.text = itemView.ctx.getString(R.string.seriesNumber, series.available.toString())
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }
}