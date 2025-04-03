package com.pivnoydevelopment.common.domain.network.api

import com.pivnoydevelopment.common.domain.models.Course
import kotlinx.coroutines.flow.Flow

interface CoursesInteractor {
    fun fetchCourses(id: String): Flow<Pair<List<Course>?, String?>>
}