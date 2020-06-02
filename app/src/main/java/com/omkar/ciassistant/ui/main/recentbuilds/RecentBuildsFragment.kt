package com.omkar.ciassistant.ui.main.recentbuilds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.omkar.ciassistant.databinding.FragmentRecentBuildsBinding
import com.omkar.core.result.EventObserver
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class RecentBuildsFragment : DaggerFragment() {

    // -----------------------------------------------------------------------------------------
    // Properties
    // -----------------------------------------------------------------------------------------

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val recentBuildsViewModel: RecentBuildsViewModel by viewModels { viewModelFactory }

    private var _binding: FragmentRecentBuildsBinding? = null
    private val binding: FragmentRecentBuildsBinding
        get() = _binding!!

    private var snackbar: Snackbar? = null

    private val adapter = RecentBuildsAdapter()

    // -----------------------------------------------------------------------------------------
    // Fragment implementation
    // -----------------------------------------------------------------------------------------

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecentBuildsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recentBuildsViewModel.pipelines.observe(
            viewLifecycleOwner,
            Observer { pipelinesResult ->
                pipelinesResult ?: return@Observer
                binding.progress.isVisible = false
                pipelinesResult.success?.let {
                    showLoadPipelinesSuccess(it)
                }
            }
        )

//        recentBuildsViewModel.recentBuildsResult.observe(
//            viewLifecycleOwner,
//            Observer { circleCiRecentBuildsResult ->
//                circleCiRecentBuildsResult ?: return@Observer
//                binding.progress.isVisible = false
//                circleCiRecentBuildsResult.success?.let {
//                    showLoadCircleCiRecentBuildsSuccess(it)
//                }
//            }
//        )

        recentBuildsViewModel.snackbar.observe(
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

    private fun showLoadCircleCiRecentBuildsSuccess(success: RecentBuildsSuccess) {
        //adapter.submitList(success.recentBuilds)
    }

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
