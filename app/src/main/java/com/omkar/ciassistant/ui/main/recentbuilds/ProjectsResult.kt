package com.omkar.ciassistant.ui.main.recentbuilds

import com.omkar.core.data.model2.Project

data class ProjectsResult(
    val success: ProjectsSuccess?,
    val error: Int? = null
)

data class ProjectsSuccess(
    val projects: List<Project>?
)
