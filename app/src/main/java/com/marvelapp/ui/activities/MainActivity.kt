package com.marvelapp.ui.activities

import android.graphics.Color
import android.os.Bundle
import android.os.StrictMode
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.marvelapp.R
import com.marvelapp.domain.commands.RequestCharactersCommand
import com.marvelapp.extensions.*
import com.marvelapp.ui.adapters.CharactersListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class MainActivity : CoroutineScopeActivity(), ToolbarManager {

    private var limitCurrent: Int by DelegatesExt.preference(
        this, SettingsActivity.LIMIT, SettingsActivity.DEFAULT_LIMIT)

    private var orderCurrent: String by DelegatesExt.preference(
        this, SettingsActivity.ORDER, SettingsActivity.DEFAULT_ORDER)

    private var updatedSettingsCurrent: Boolean by DelegatesExt.preference(
        this, SettingsActivity.UPDATED_SETTINGS, SettingsActivity.DEFAULT_UPDATED_SETTINGS)

    companion object {
        var numberRetries = 0
    }

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        initToolbar()

        charactersList.layoutManager = LinearLayoutManager(this)
        attachToScroll(charactersList)
        loadCharacters()
    }

    override fun onResume() {
        super.onResume()

        if (updatedSettingsCurrent) {
            loadCharacters()
            updatedSettingsCurrent = false
        }
    }

    private fun loadCharacters() = launch {
        val result = RequestCharactersCommand(orderCurrent, limitCurrent).execute()

        if (result != null) {
            val adapter = CharactersListAdapter(result) {
                startActivity<DetailActivity>(DetailActivity.CHARACTER_ID to it.id)
            }
            charactersList.adapter = adapter
            toolbarTitle = "${getString(R.string.toolbarIndex)} ${getCurrentDate()}"
            numberRetries = 0
        } else { if (getRetry()) showErrorAndRetry(findViewById(R.id.frameLayout)) else toast(MESSAGE_MAX_RETRIES) }
    }

    private fun showErrorAndRetry(view: View) {
        Snackbar.make(view, MESSAGE_ERROR, Snackbar.LENGTH_LONG)
            .setAction("Retry"){ loadCharacters() }
            .setActionTextColor(Color.RED)
            .show()
    }

    private fun getRetry(): Boolean {
        numberRetries += 1
        return numberRetries <= MAX_RETRIES
    }
}