package com.example.perempuan.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.example.perempuan.databinding.ActivitySettingsBinding
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.PublishSubject

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private lateinit var saveData: SaveData
    private lateinit var stream: PublishSubject<Boolean>
    private lateinit var subscriber: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        stream = PublishSubject.create()
        subscriber = stream
            .subscribe { it ->
                saveData.setDarkModeState(it)
                if(it){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }

        var switch = binding.swithDarkMode

        switch.setOnCheckedChangeListener { _, isChecked ->
            stream.onNext(isChecked)
        }

        saveData = SaveData(this)
        if(saveData.loadDarkModeState()) {
            switch.setChecked(true)
        } else {
            switch.setChecked(false)
        }

        binding.btnBack.setOnClickListener{
            onBackPressed()
        }
    }

    override fun onStop() {
        stream.onComplete()
        subscriber.dispose()
        super.onStop()
    }
}