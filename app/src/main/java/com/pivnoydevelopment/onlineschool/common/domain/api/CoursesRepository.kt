package com.pivnoydevelopment.onlineschool.common.domain.api

import com.pivnoydevelopment.onlineschool.common.domain.models.Course
import com.pivnoydevelopment.onlineschool.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CoursesRepository {
    fun fetchCourses(id: String): Flow<Resource<List<Course>>>
}