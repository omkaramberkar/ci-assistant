package com.omkar.ciassistant.ui.registration

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import coil.api.load
import coil.transform.CircleCropTransformation
import com.google.android.material.snackbar.Snackbar
import com.omkar.ciassistant.R
import com.omkar.ciassistant.databinding.FragmentTokenRegistrationBinding
import com.omkar.ciassistant.ui.LaunchDestination
import com.omkar.ciassistant.ui.main.MainActivity
import com.omkar.core.result.EventObserver
import com.omkar.core.util.checkAllMatched
import com.omkar.core.util.launchUrl
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class TokenRegistrationFragment : DaggerFragment() {

    // -----------------------------------------------------------------------------------------
    // Properties
    // -----------------------------------------------------------------------------------------

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val tokenRegistrationViewModel: TokenRegistrationViewModel
            by viewModels { viewModelFactory }

    private var _binding: FragmentTokenRegistrationBinding? = null
    private val binding: FragmentTokenRegistrationBinding
        get() = _binding!!

    private var snackbar: Snackbar? = null

    // -----------------------------------------------------------------------------------------
    // Fragment implementation
    // -----------------------------------------------------------------------------------------

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTokenRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tokenRegistrationViewModel.tokenRegistrationFormState.observe(
            viewLifecycleOwner,
            Observer { tokenRegistrationFormState ->
                tokenRegistrationFormState ?: return@Observer
                binding.registerTokenButton.isEnabled =
                    tokenRegistrationFormState.isDataValid
                tokenRegistrationFormState.circleCiTokenError?.let {
                    binding.registerTokenLayout.error = getString(it)
                }
            }
        )

        tokenRegistrationViewModel.tokenRegistrationResult.observe(
            viewLifecycleOwner,
            Observer { tokenRegistrationResult ->
                tokenRegistrationResult ?: return@Observer
                binding.progressBar.isVisible = false
                binding.registerTokenButton.isEnabled = true
                tokenRegistrationResult.success?.let {
                    showCircleCiTokenRegistrationSuccess(it)
                }
            }
        )

        tokenRegistrationViewModel.snackbar.observe(viewLifecycleOwner, EventObserver { string ->
            showSnackbar(string)
        })

        tokenRegistrationViewModel.launchDestination.observe(
            viewLifecycleOwner,
            EventObserver { launchDestination ->
                when (launchDestination) {
                    LaunchDestination.MAIN_ACTIVITY ->
                        activity?.let { startActivity(Intent(it, MainActivity::class.java)) }
                    else -> throw IllegalStateException("Launch Destination is not supported")
                }.checkAllMatched
            })

        with(binding.registerTokenInputText) {
            doAfterTextChanged {
                tokenRegistrationViewModel.tokenUpdated(text.toString().trim())
            }
            setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    startCircleCiUserRegistration(text.toString().trim())
                }
                return@setOnEditorActionListener false
            }
        }

        binding.registerTokenButton.setOnClickListener {
            startCircleCiUserRegistration(
                binding.registerTokenInputText.text.toString().trim()
            )
        }

        binding.createTokenButton.setOnClickListener {
            activity?.launchUrl(CIRCLE_CI_ACCOUNT_API)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // -----------------------------------------------------------------------------------------
    // Private functions
    // -----------------------------------------------------------------------------------------

    private fun startCircleCiUserRegistration(circleCiToken: String) {
        binding.progressBar.isVisible = true
        binding.registerTokenButton.isEnabled = false
        tokenRegistrationViewModel.registerToken(circleCiToken)
    }

    private fun showCircleCiTokenRegistrationSuccess(success: TokenRegistrationSuccess) {
        binding.registerTokenAvatar.load(success.avatarUrl) {
            crossfade(true)
            placeholder(R.drawable.ic_round_account_circle_24)
            transformations(CircleCropTransformation())
        }
    }

    private fun showSnackbar(string: String) {
        snackbar = Snackbar.make(binding.root, string, Snackbar.LENGTH_LONG)
        snackbar?.show()
    }

    // -----------------------------------------------------------------------------------------
    // Companion
    // -----------------------------------------------------------------------------------------

    companion object {
        private const val CIRCLE_CI_ACCOUNT_API = "https://circleci.com/account/api"
    }
}
