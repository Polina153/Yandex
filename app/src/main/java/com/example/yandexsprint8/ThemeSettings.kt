package com.example.yandexsprint8

import android.content.Intent

data class ThemeSettings(
    val theme: String,
    val textSize: String,
    val accentColor: String
) {

    fun applyToIntent(intent: Intent) {
        intent.putExtra(EXTRA_THEME, theme)
        intent.putExtra(EXTRA_TEXT_SIZE, textSize)
        intent.putExtra(EXTRA_ACCENT_COLOR, accentColor)
    }

    companion object {
        const val EXTRA_THEME = "extra_theme"
        const val EXTRA_TEXT_SIZE = "extra_text_size"
        const val EXTRA_ACCENT_COLOR = "extra_accent_color"

        const val THEME_LIGHT = "LIGHT"
        const val THEME_DARK = "DARK"
        const val THEME_CONTRAST = "CONTRAST"

        const val TEXT_SIZE_SMALL = "SMALL"
        const val TEXT_SIZE_MEDIUM = "MEDIUM"
        const val TEXT_SIZE_LARGE = "LARGE"

        const val ACCENT_BLUE = "BLUE"
        const val ACCENT_GREEN = "GREEN"
        const val ACCENT_ORANGE = "ORANGE"

        fun fromIntent(intent: Intent): ThemeSettings {
            return ThemeSettings(
                theme = intent.getStringExtra(EXTRA_THEME) ?: THEME_LIGHT,
                textSize = intent.getStringExtra(EXTRA_TEXT_SIZE) ?: TEXT_SIZE_MEDIUM,
                accentColor = intent.getStringExtra(EXTRA_ACCENT_COLOR) ?: ACCENT_BLUE
            )
        }
    }
}
