package com.omkar.ciassistant.ui.main.recentbuilds

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omkar.ciassistant.R
import com.omkar.core.data.model.Build
import com.omkar.core.data.model2.Pipeline
import com.omkar.core.data.model2.Project
import com.omkar.core.domain.GetCircleCIPipelinesUseCase
import com.omkar.core.domain.GetCircleCIProjectsUseCase
import com.omkar.core.domain.GetCircleCIRecentBuildsUseCase
import com.omkar.core.result.Event
import com.omkar.core.result.Result
import kotlinx.coroutines.launch
import javax.inject.Inject

class RecentBuildsViewModel @Inject constructor(
    private val resources: Resources,
    private val getCircleCIRecentBuildsUseCase: GetCircleCIRecentBuildsUseCase,
    private val getCircleCIProjectsUseCase: GetCircleCIProjectsUseCase,
    private val getCircleCIPipelinesUseCase: GetCircleCIPipelinesUseCase
) : ViewModel() {

    // -----------------------------------------------------------------------------------------
    // Properties
    // -----------------------------------------------------------------------------------------

    private val _recentBuildsResult = MutableLiveData<RecentBuildsResult>()
    val recentBuildsResult: LiveData<RecentBuildsResult> = _recentBuildsResult

    private val _projects = MutableLiveData<ProjectsResult>()
    val projects: LiveData<ProjectsResult> = _projects

    private val _pipelines = MutableLiveData<PipelinesResult>()
    val pipelines: LiveData<PipelinesResult> = _pipelines

    private val _snackbar = MutableLiveData<Event<String>>()
    val snackbar: LiveData<Event<String>> = _snackbar

    init {
        loadProjects(true)
    }

    // -----------------------------------------------------------------------------------------
    // Public functions
    // -----------------------------------------------------------------------------------------

    fun loadProjects(forceUpdate: Boolean) {
        viewModelScope.launch {
            when (val result = getCircleCIProjectsUseCase(forceUpdate)) {
                is Result.Success -> onLoadProjectsSuccess(result.data)
                is Result.Error -> onLoadProjectsError()
            }
        }
    }

    fun loadRecentBuilds(forceUpdate: Boolean) {
        viewModelScope.launch {
            when (val result = getCircleCIRecentBuildsUseCase(forceUpdate)) {
                is Result.Success -> onLoadCircleCiRecentBuildsSuccess(result.data)
                is Result.Error -> onLoadCircleCiRecentBuildsError()
            }
        }
    }

    fun loadPipelines(vcsType: String, username: String, project: String) {
        viewModelScope.launch {
            when (val result = getCircleCIPipelinesUseCase(vcsType, username, project)) {
                is Result.Success -> onLoadPipelinesSuccess(result.data)
                is Result.Error -> onLoadPipelinesError()
            }
        }
    }

    // -----------------------------------------------------------------------------------------
    // Private functions
    // -----------------------------------------------------------------------------------------

    private fun onLoadProjectsSuccess(data: List<Project>) {
        _projects.value = ProjectsResult(
            success = ProjectsSuccess(
                projects = data
            )
        )
        // Only loading pipelines for first project
        val project = data.firstOrNull()
        project?.let {
            loadPipelines(project.vcsType, project.username, project.repoName)
        }
    }

    private fun onLoadProjectsError() {
        _snackbar.value = Event(resources.getString(R.string.loading_projects_failed))
    }

    private fun onLoadPipelinesSuccess(data: List<Pipeline>) {
        _pipelines.value = PipelinesResult(
            success = PipelinesSuccess(
                pipelines = data
            )
        )
    }

    private fun onLoadPipelinesError() {
        _snackbar.value = Event(resources.getString(R.string.loading_pipelines_failed))
    }

    private fun onLoadCircleCiRecentBuildsSuccess(data: List<Build>) {
        _recentBuildsResult.value = RecentBuildsResult(
            success = RecentBuildsSuccess(
                recentBuilds = data
            )
        )
    }

    private fun onLoadCircleCiRecentBuildsError() {
        _snackbar.value = Event(resources.getString(R.string.loading_recent_builds_failed))
    }
}
