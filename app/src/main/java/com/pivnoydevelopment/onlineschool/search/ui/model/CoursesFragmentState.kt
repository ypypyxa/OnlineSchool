package com.pivnoydevelopment.onlineschool.search.ui.model

import com.pivnoydevelopment.onlineschool.common.domain.models.Course

sealed interface CoursesFragmentState {
    object Loading : CoursesFragmentState

    data class Content(
        val courses: List<Course>
    ) : CoursesFragmentState

    data class Error(
        val errorMessage: String
    ) : CoursesFragmentState

    data class Empty(
        val message: String
    ) : CoursesFragmentState
}