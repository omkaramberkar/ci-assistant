package com.omkar.ciassistant

import android.os.Bundle
import com.omkar.ciassistant.databinding.ActivityLauncherBinding
import dagger.android.support.DaggerAppCompatActivity

class LauncherActivity : DaggerAppCompatActivity() {

    // -----------------------------------------------------------------------------------------
    // Properties
    // -----------------------------------------------------------------------------------------

    private lateinit var binding: ActivityLauncherBinding

    // -----------------------------------------------------------------------------------------
    // AppCompatActivity implementation
    // -----------------------------------------------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLauncherBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
