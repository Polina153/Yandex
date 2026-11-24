package com.example.yandexsprint8

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class MainActivity : AppCompatActivity() {

    private lateinit var themeChipGroup: ChipGroup
    private lateinit var textSizeChipGroup: ChipGroup
    private lateinit var accentColorChipGroup: ChipGroup
    private lateinit var applyButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }*/

        initViews()
        setupChips(
            chipGroup = themeChipGroup,
            arrayResId = R.array.theme_options
        )

        setupChips(
            chipGroup = textSizeChipGroup,
            arrayResId = R.array.text_size_options
        )

        setupChips(
            chipGroup = accentColorChipGroup,
            arrayResId = R.array.accent_color_options
        )

        applyButton.setOnClickListener {
            val selectedThemeTitle = getSelectedChipText(themeChipGroup)
            val selectedTextSizeTitle = getSelectedChipText(textSizeChipGroup)
            val selectedAccentColorTitle = getSelectedChipText(accentColorChipGroup)

            val theme = when (selectedThemeTitle) {
                getString(R.string.theme_dark) -> ThemeSettings.THEME_DARK
                getString(R.string.theme_contrast) -> ThemeSettings.THEME_CONTRAST
                else -> ThemeSettings.THEME_LIGHT
            }

            val textSize = when (selectedTextSizeTitle) {
                getString(R.string.text_size_small) -> ThemeSettings.TEXT_SIZE_SMALL
                getString(R.string.text_size_large) -> ThemeSettings.TEXT_SIZE_LARGE
                else -> ThemeSettings.TEXT_SIZE_MEDIUM
            }

            val accentColor = when (selectedAccentColorTitle) {
                getString(R.string.accent_color_green) -> ThemeSettings.ACCENT_GREEN
                getString(R.string.accent_color_orange) -> ThemeSettings.ACCENT_ORANGE
                else -> ThemeSettings.ACCENT_BLUE
            }

            val settings = ThemeSettings(
                theme = theme,
                textSize = textSize,
                accentColor = accentColor
            )

            val intent = Intent(this, ResultActivity::class.java)
            settings.applyToIntent(intent)

            startActivity(intent)
        }
    }

    private fun initViews() {
        themeChipGroup = findViewById(R.id.themeChipGroup)
        textSizeChipGroup = findViewById(R.id.textSizeChipGroup)
        accentColorChipGroup = findViewById(R.id.accentColorChipGroup)
        applyButton = findViewById(R.id.applyButton)
    }

    private fun setupChips(
        chipGroup: ChipGroup,
        arrayResId: Int
    ) {
        val options = resources.getStringArray(arrayResId)

        options.forEachIndexed { index, title ->
            val chip = Chip(this).apply {
                id = View.generateViewId()
                text = title
                isCheckable = true
                isChecked = index == 0
            }

            chipGroup.addView(chip)
        }
    }

    private fun getSelectedChipText(chipGroup: ChipGroup): String {
        val checkedChipId = chipGroup.checkedChipId

        return if (checkedChipId != View.NO_ID) {
            findViewById<Chip>(checkedChipId).text.toString()
        } else {
            ""
        }
    }
}
