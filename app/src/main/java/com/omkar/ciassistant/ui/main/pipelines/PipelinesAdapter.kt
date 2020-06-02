package com.omkar.ciassistant.ui.main.pipelines

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.omkar.ciassistant.R
import com.omkar.ciassistant.databinding.ItemRecentBuildBinding
import com.omkar.ciassistant.ui.main.pipelines.PipelinesAdapter.CircleCiRecentBuildViewHolder
import com.omkar.core.data.model2.Pipeline

class PipelinesAdapter
    : ListAdapter<Pipeline, CircleCiRecentBuildViewHolder>(PipelinesDiffCallback()) {

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
            val resources = binding.pipelineNumberText.resources
            binding.pipelineNumberText.text = String.format(
                resources.getString(R.string.pipeline_number),
                pipeline.number.toString()
            )
            setPipelineStatus(pipeline)
            binding.pipelineWorkflowNameText.text = pipeline.workflows?.firstOrNull()?.name
            binding.pipelineVcsBranchText.text = pipeline.vcs?.branch
            binding.pipelineTriggerActorAvatarUrlImage.load(pipeline.trigger?.actor?.avatarUrl) {
                crossfade(true)
                placeholder(R.drawable.ic_round_account_circle_24)
                transformations(CircleCropTransformation())
            }
            binding.pipelineTriggerActorLoginText.text = pipeline.trigger?.actor?.login
            binding.pipelineVcsCommitSubjectText.text = pipeline.vcs?.commit?.subject
        }

        // -----------------------------------------------------------------------------------------
        // Private functions
        // -----------------------------------------------------------------------------------------

        private fun setPipelineStatus(pipeline: Pipeline) {
            val lastWorkflow = pipeline.workflows?.last()
            lastWorkflow?.let { workflow ->
                when (workflow.status) {
                    QUEUED -> setTextViewCircleCiBuildOutcome(
                        R.color.notRunning,
                        R.string.queued,
                        R.drawable.ic_round_remove_circle_24
                    )
                    NOT_RUN -> setTextViewCircleCiBuildOutcome(
                        R.color.notRun,
                        R.string.not_run,
                        R.drawable.ic_round_remove_circle_24
                    )
                    NOT_RUNNING -> setTextViewCircleCiBuildOutcome(
                        R.color.notRunning,
                        R.string.not_running,
                        R.drawable.ic_round_more_horiz_24
                    )
                    RUNNING -> setTextViewCircleCiBuildOutcome(
                        R.color.running,
                        R.string.running,
                        R.drawable.ic_round_radio_button_checked_24
                    )
                    SUCCESS -> setTextViewCircleCiBuildOutcome(
                        R.color.success,
                        R.string.success,
                        R.drawable.ic_round_check_circle_24
                    )
                    FAILED -> setTextViewCircleCiBuildOutcome(
                        R.color.failed,
                        R.string.failed,
                        R.drawable.ic_round_error_24
                    )
                }
            }
        }

        private fun setTextViewCircleCiBuildOutcome(
            @ColorRes colorRes: Int,
            @StringRes stringRes: Int,
            @DrawableRes drawableRes: Int
        ) {
            with(binding.pipelineStatus) {
                setBackgroundColor(resources.getColor(colorRes, null))
                text = resources.getString(stringRes)
                icon = ResourcesCompat.getDrawable(resources, drawableRes, null)
            }
        }
    }

    companion object {
        private const val QUEUED = "queued"
        private const val NOT_RUN = "not_run"
        private const val NOT_RUNNING = "not_running"
        private const val RUNNING = "running"
        private const val SUCCESS = "success"
        private const val FAILED = "failed"
    }
}
