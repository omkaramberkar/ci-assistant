package com.omkar.ciassistant.ui.loading

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.omkar.ciassistant.R
import com.omkar.ciassistant.databinding.FragmentLoadingBinding
import com.omkar.ciassistant.ui.LaunchDestination
import com.omkar.core.result.EventObserver
import com.omkar.core.util.checkAllMatched
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class LoadingFragment : DaggerFragment() {

    // -----------------------------------------------------------------------------------------
    // Properties
    // -----------------------------------------------------------------------------------------

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val launcherViewModel: LoadingViewModel by viewModels { viewModelFactory }

    private var _binding: FragmentLoadingBinding? = null
    private val binding: FragmentLoadingBinding
        get() = _binding!!

    // -----------------------------------------------------------------------------------------
    // Fragment implementation
    // -----------------------------------------------------------------------------------------

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoadingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        launcherViewModel.launchDestination.observe(
            viewLifecycleOwner,
            EventObserver { destination ->
                val destinationId = when (destination) {
                    LaunchDestination.TOKEN_REGISTRATION_FRAGMENT ->
                        R.id.action_loading_to_token_registration
                    LaunchDestination.MAIN_ACTIVITY ->
                        R.id.action_loading_to_token_registration
                }.checkAllMatched
                findNavController().navigate(destinationId)
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
