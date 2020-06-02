package com.omkar.ciassistant.ui.main.pipelines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.omkar.ciassistant.databinding.FragmentPipelinesBinding
import com.omkar.core.result.EventObserver
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class PipelinesFragment : DaggerFragment() {

    // -----------------------------------------------------------------------------------------
    // Properties
    // -----------------------------------------------------------------------------------------

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val pipelinesViewModel: PipelinesViewModel by viewModels { viewModelFactory }

    private var _binding: FragmentPipelinesBinding? = null
    private val binding: FragmentPipelinesBinding
        get() = _binding!!

    private var snackbar: Snackbar? = null

    private val adapter = PipelinesAdapter()

    // -----------------------------------------------------------------------------------------
    // Fragment implementation
    // -----------------------------------------------------------------------------------------

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPipelinesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progress.isVisible = true

        pipelinesViewModel.pipelines.observe(
            viewLifecycleOwner,
            Observer { pipelinesResult ->
                pipelinesResult ?: return@Observer
                binding.progress.isVisible = false
                pipelinesResult.success?.let {
                    showLoadPipelinesSuccess(it)
                }
            }
        )

        pipelinesViewModel.snackbar.observe(
            viewLifecycleOwner,
            EventObserver { string ->
                showSnackbar(string)
            }
        )

        binding.recentBuildsList.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        snackbar = null
        _binding = null
    }

    // -----------------------------------------------------------------------------------------
    // Private functions
    // -----------------------------------------------------------------------------------------

    private fun showLoadPipelinesSuccess(success: PipelinesSuccess) {
        success.pipelines?.let {
            adapter.submitList(it)
        }
    }

    private fun showSnackbar(string: String) {
        snackbar = Snackbar.make(binding.root, string, Snackbar.LENGTH_LONG)
        snackbar?.show()
    }
}
