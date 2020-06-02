package com.omkar.ciassistant.ui.main.pipelines

import androidx.recyclerview.widget.DiffUtil
import com.omkar.core.data.model2.Pipeline

class PipelinesDiffCallback : DiffUtil.ItemCallback<Pipeline>() {

    // -----------------------------------------------------------------------------------------
    // DiffUtil.ItemCallback implementation
    // -----------------------------------------------------------------------------------------

    override fun areItemsTheSame(oldItem: Pipeline, newItem: Pipeline): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Pipeline, newItem: Pipeline): Boolean {
        return oldItem == newItem
    }
}
