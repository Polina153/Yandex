package com.example.yandexsprint8

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {

    private lateinit var root: LinearLayout
    private lateinit var settingsTitleText: TextView
    private lateinit var themeText: TextView
    private lateinit var backButton: Button
    private lateinit var textSizeText: TextView
    private lateinit var accentColorText: TextView
    private lateinit var titleText: TextView
    private lateinit var settingsCard: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rootLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initViews()

        val settings = ThemeSettings.fromIntent(intent)

        applyTheme(settings.theme)
        applyTextSize(settings.textSize)
        applyAccentColor(settings.accentColor)

        showSelectedSettings(settings)

        backButton.setOnClickListener {
            finish()
        }
    }

    private fun applyTheme(theme: String) {
        val backgroundColorResId = when (theme) {
            ThemeSettings.THEME_DARK -> R.color.theme_dark
            ThemeSettings.THEME_CONTRAST -> R.color.theme_contrast
            else -> R.color.theme_light
        }

        val titleColorResId = when (theme) {
            ThemeSettings.THEME_DARK -> R.color.text_primary_dark
            ThemeSettings.THEME_CONTRAST -> R.color.text_primary_contrast
            else -> R.color.text_primary_light
        }

        val secondaryColorResId = when (theme) {
            ThemeSettings.THEME_DARK -> R.color.text_secondary_dark
            ThemeSettings.THEME_CONTRAST -> R.color.text_secondary_contrast
            else -> R.color.text_secondary_light
        }

        val backgroundColor = ContextCompat.getColor(this, backgroundColorResId)
        val titleColor = ContextCompat.getColor(this, titleColorResId)
        val secondaryColor = ContextCompat.getColor(this, secondaryColorResId)

        root.setBackgroundColor(backgroundColor)
        settingsCard.setBackgroundColor(backgroundColor)

        titleText.setTextColor(titleColor)
        settingsTitleText.setTextColor(titleColor)

        themeText.setTextColor(secondaryColor)
        textSizeText.setTextColor(secondaryColor)
        accentColorText.setTextColor(secondaryColor)
    }

    private fun applyTextSize(textSize: String) {
        val textSizeResId = when (textSize) {
            ThemeSettings.TEXT_SIZE_SMALL -> R.dimen.text_small
            ThemeSettings.TEXT_SIZE_LARGE -> R.dimen.text_large
            else -> R.dimen.text_medium
        }

        val textSize = resources.getDimension(textSizeResId)

        titleText.textSize = textSize
        settingsTitleText.textSize = textSize
        themeText.textSize = textSize
        textSizeText.textSize = textSize
        accentColorText.textSize = textSize
    }

    private fun applyAccentColor(accentColor: String) {
        val accentColorResId = when (accentColor) {
            ThemeSettings.ACCENT_GREEN -> R.color.accent_green
            ThemeSettings.ACCENT_ORANGE -> R.color.accent_orange
            else -> R.color.accent_blue
        }

        val accentColor = ContextCompat.getColor(this, accentColorResId)

        backButton.setBackgroundColor(accentColor)
        backButton.setTextColor(Color.WHITE)
    }

    private fun showSelectedSettings(settings: ThemeSettings) {
        val themeTitle = when (settings.theme) {
            ThemeSettings.THEME_DARK -> getString(R.string.theme_dark)
            ThemeSettings.THEME_CONTRAST -> getString(R.string.theme_contrast)
            else -> getString(R.string.theme_light)
        }

        val textSizeTitle = when (settings.textSize) {
            ThemeSettings.TEXT_SIZE_SMALL -> getString(R.string.text_size_small)
            ThemeSettings.TEXT_SIZE_LARGE -> getString(R.string.text_size_large)
            else -> getString(R.string.text_size_medium)
        }

        val accentColorTitle = when (settings.accentColor) {
            ThemeSettings.ACCENT_GREEN -> getString(R.string.accent_color_green)
            ThemeSettings.ACCENT_ORANGE -> getString(R.string.accent_color_orange)
            else -> getString(R.string.accent_color_blue)
        }

        themeText.text = getString(
            R.string.theme_format,
            themeTitle
        )

        textSizeText.text = getString(
            R.string.text_size_format,
            textSizeTitle
        )

        accentColorText.text = getString(
            R.string.accent_color_format,
            accentColorTitle
        )
    }

    private fun initViews() {
        root = findViewById(R.id.rootLayout)
        settingsTitleText = findViewById(R.id.settingsTitleText)
        themeText = findViewById(R.id.themeText)
        backButton = findViewById(R.id.backButton)
        textSizeText = findViewById(R.id.textSizeText)
        accentColorText = findViewById(R.id.accentColorText)
        titleText = findViewById(R.id.titleText)
        settingsCard = findViewById(R.id.settingsCard)
    }
}
