package com.pivnoydevelopment.onlineschool.common.domain.api

import com.pivnoydevelopment.onlineschool.common.domain.models.Course
import kotlinx.coroutines.flow.Flow

interface CoursesInteractor {
    fun fetchCourses(id: String): Flow<Pair<List<Course>?, String?>>
}