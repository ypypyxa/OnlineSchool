package com.pivnoydevelopment.onlineschool.courses.ui.model

import com.pivnoydevelopment.common.domain.models.Course

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

    data class Sort(
        val courses: List<Course>
    ) : CoursesFragmentState
}