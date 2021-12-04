package com.example.perempuan.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import com.example.perempuan.R
import com.example.perempuan.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var saveData: SaveData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        var switch = findViewById<Switch>(R.id.swithDarkMode)

        saveData = SaveData(this)
        if(saveData.loadDarkModeState()) {
            switch.setChecked(true)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            switch.setChecked(false)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        switch.setOnCheckedChangeListener { _, isChecked ->
             if (isChecked) {
                 saveData.setDarkModeState(true)
                 restart()
             } else {
                 saveData.setDarkModeState(false)
                 restart()
             }
         }

        findViewById<Button>(R.id.btnBack).setOnClickListener{
            onBackPressed()
        }
    }

    private fun restart() {
        startActivity(Intent(applicationContext, SettingsActivity::class.java))
        overridePendingTransition(0,0)
        finish()
    }
}