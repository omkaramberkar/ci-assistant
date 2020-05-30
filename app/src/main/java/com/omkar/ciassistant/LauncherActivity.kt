package com.omkar.ciassistant

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.omkar.ciassistant.databinding.ActivityLauncherBinding
import com.omkar.core.result.EventObserver
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class LauncherActivity : DaggerAppCompatActivity() {

    // -----------------------------------------------------------------------------------------
    // Properties
    // -----------------------------------------------------------------------------------------

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val launcherViewModel: LauncherViewModel by viewModels { viewModelFactory }

    private lateinit var binding: ActivityLauncherBinding

    // -----------------------------------------------------------------------------------------
    // AppCompatActivity implementation
    // -----------------------------------------------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLauncherBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        launcherViewModel.launchDestination.observe(
//            this,
//            EventObserver { destination ->
//                binding.progressCircleCiToken.isVisible = false
//                val startDestinationId = when (destination) {
//                    LaunchDestination.TOKEN_REGISTRATION_FRAGMENT ->
//                    LaunchDestination.MAIN_ACTIVITY ->
//                }.checkAllMatched
//            }
//        )
    }
}
