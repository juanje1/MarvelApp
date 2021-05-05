package com.marvelapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.SeekBar
import androidx.appcompat.app.AlertDialog
import com.marvelapp.R
import com.marvelapp.extensions.DelegatesExt
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.toast

class SettingsActivity : AppCompatActivity() {

    companion object {
        const val LIMIT = "limit"
        const val DEFAULT_LIMIT = 25
        const val ORDER = "order"
        const val DEFAULT_ORDER = "name"
        const val UPDATED_SETTINGS = "updated"
        const val DEFAULT_UPDATED_SETTINGS = false
    }

    private var limitCurrent: Int by DelegatesExt.preference(this, LIMIT, DEFAULT_LIMIT)
    private var orderCurrent: String by DelegatesExt.preference(this, ORDER, DEFAULT_ORDER)
    private var updatedSettingsCurrent: Boolean by DelegatesExt.preference(this, UPDATED_SETTINGS, DEFAULT_UPDATED_SETTINGS)
    private val orderValues = arrayOf("Name Ascending", "Name Descending", "Modified Ascending", "Modified Descending")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initializeViews()
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            save()
            true
        }
        else -> false
    }

    private fun initializeViews() {
        orderSpinner.adapter = ArrayAdapter(this@SettingsActivity, android.R.layout.simple_list_item_1, orderValues)
        orderSpinner.setSelection(showSelectionDefaultOrder())
        limitSeekBar.progress = limitCurrent
        currentLimitTextView.text = limitCurrent.toString()

        limitSeekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                currentLimitTextView.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) { }

            override fun onStopTrackingTouch(seekBar: SeekBar?) { }
        })
    }

    private fun showSelectionDefaultOrder() =
        when(orderCurrent) {
            "name" -> 0
            "-name" -> 1
            "modified" -> 2
            "-modified" -> 3
            else -> 0
        }

    private fun setValueOrder() =
        when(orderSpinner.selectedItem) {
            "Name Ascending" -> "name"
            "Name Descending" -> "-name"
            "Modified Ascending" -> "modified"
            "Modified Descending" -> "-modified"
            else -> "name"
        }

    private fun save() {
        with(AlertDialog.Builder(this)) {
            setTitle("Save Changes")
            setMessage("Do you want to save changes?")
            setPositiveButton("Yes") { _, _ ->
                orderCurrent = setValueOrder()
                try {
                    limitCurrent = currentLimitTextView.text.toString().toInt()
                } catch (exception: NumberFormatException) {
                    toast("Error: Limit not numeric")
                }
                updatedSettingsCurrent = true
                toast("Settings saved")

                onBackPressed()
            }
            setNegativeButton("No") { _, _ -> onBackPressed() }
            setNeutralButton("Cancel") { _, _ -> setCancelable(true)}
            show()
        }
    }
}