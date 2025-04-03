package com.pivnoydevelopment.common.domain.network.api

import com.pivnoydevelopment.common.domain.models.Course
import com.pivnoydevelopment.common.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CoursesRepository {
    fun fetchCourses(id: String): Flow<Resource<List<Course>>>
}