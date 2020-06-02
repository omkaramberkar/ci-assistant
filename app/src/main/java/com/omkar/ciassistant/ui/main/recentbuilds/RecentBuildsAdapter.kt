package com.omkar.ciassistant.ui.main.recentbuilds

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.omkar.ciassistant.databinding.ItemRecentBuildBinding
import com.omkar.ciassistant.ui.main.recentbuilds.RecentBuildsAdapter.CircleCiRecentBuildViewHolder
import com.omkar.core.data.model2.Pipeline

class RecentBuildsAdapter
    : ListAdapter<Pipeline, CircleCiRecentBuildViewHolder>(RecentBuildsDiffCallback()) {

    // -----------------------------------------------------------------------------------------
    // ListAdapter implementation
    // -----------------------------------------------------------------------------------------

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CircleCiRecentBuildViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRecentBuildBinding.inflate(layoutInflater, parent, false)
        return CircleCiRecentBuildViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CircleCiRecentBuildViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    // -----------------------------------------------------------------------------------------
    // RecyclerView.ViewHolder
    // -----------------------------------------------------------------------------------------

    inner class CircleCiRecentBuildViewHolder(
        private val binding: ItemRecentBuildBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        // -----------------------------------------------------------------------------------------
        // Public functions
        // -----------------------------------------------------------------------------------------

        fun bind(pipeline: Pipeline) {
//            binding.root.setOnClickListener { recentBuildClickListener(pipeline) }
//
//            binding.rebuildButton.setOnClickListener {
//                recentBuildRebuildFailedClickListener(pipeline)
//            }
//
//            binding.userAvatar.load(pipeline.trigger?.actor?.avatarUrl) {
//                crossfade(true)
//                placeholder(R.drawable.ic_round_account_circle_24)
//                transformations(CircleCropTransformation())
//            }

//            binding.buildUrlText.text =
//                "${pipeline.username}/${build.reponame}/${build.branch}/#${build.buildNum}"

//            build.subject?.let {
//                with(binding.buildSubjectText) {
//                    isVisible = true
//                    text = it
//                }
//            }
//            pipeline.workflows?.let {
//                with(binding.buildWorkflowText) {
//                    isVisible = true
//                    text = it.workflowName
//                }
//                with(binding.buildNameText) {
//                    isVisible = true
//                    text = it.jobName
//                }
//            }
//            binding.buildStopTimeText.text = build.stopTimeToString()
//            binding.buildTimeText.text = build.buildTimeMillisToString()
//            binding.buildVcsText.text = build.vcsRevisionToString()
//
//            val vcsUrl = when {
//                !build.allCommitDetails.isNullOrEmpty() -> build.allCommitDetails?.first()?.commitUrl
//                build.vcsType == VcsType.GITHUB -> "${build.vcsUrl}/commit/${build.vcsRevision}"
//                build.vcsType == VcsType.BITBUCKET -> "${build.vcsUrl}/commits/${build.vcsRevision}"
//                else -> null
//            } ?: "${build.vcsUrl}/commit/${build.vcsRevision}"
//            binding.buildVcsText.setOnClickListener {
//                recentBuildVcsUrlClickListener(vcsUrl)
//            }
            //setTextViewCircleCiBuildOutComeStatus(build)
        }

        // -----------------------------------------------------------------------------------------
        // Private functions
        // -----------------------------------------------------------------------------------------

//        private fun setTextViewCircleCiBuildOutComeStatus(pipeline: Pipeline) {
//            when (pipeline.) {
//                Status.QUEUED -> {
//                    setTextViewCircleCiBuildOutcome(
//                        R.color.notRunning,
//                        R.string.queued,
//                        R.drawable.ic_round_remove_circle_24
//                    )
//                    binding.rebuildButton.isVisible = false
//                }
//                Status.NOT_RUN -> {
//                    setTextViewCircleCiBuildOutcome(
//                        R.color.notRun,
//                        R.string.not_run,
//                        R.drawable.ic_round_remove_circle_24
//                    )
//                    binding.rebuildButton.isVisible = false
//                }
//                Status.NOT_RUNNING -> {
//                    setTextViewCircleCiBuildOutcome(
//                        R.color.notRunning,
//                        R.string.not_running,
//                        R.drawable.ic_round_more_horiz_24
//                    )
//                    binding.rebuildButton.isVisible = false
//                }
//                Status.RUNNING -> {
//                    setTextViewCircleCiBuildOutcome(
//                        R.color.running,
//                        R.string.running,
//                        R.drawable.ic_round_radio_button_checked_24
//                    )
//                    binding.rebuildButton.isVisible = false
//                }
//                Status.SUCCESS -> {
//                    setTextViewCircleCiBuildOutcome(
//                        R.color.success,
//                        R.string.success,
//                        R.drawable.ic_round_check_circle_24
//                    )
//                    binding.rebuildButton.isVisible = false
//                }
//                Status.FAILED -> {
//                    setTextViewCircleCiBuildOutcome(
//                        R.color.failed,
//                        R.string.failed,
//                        R.drawable.ic_round_error_24
//                    )
//                }
//            }
//        }

        private fun setTextViewCircleCiBuildOutcome(
            @ColorRes colorRes: Int,
            @StringRes stringRes: Int,
            @DrawableRes drawableRes: Int
        ) {
            with(binding.buildOutcomeButton) {
                setBackgroundColor(resources.getColor(colorRes, null))
                text = resources.getString(stringRes)
                icon = ResourcesCompat.getDrawable(resources, drawableRes, null)
            }
        }
    }
}
