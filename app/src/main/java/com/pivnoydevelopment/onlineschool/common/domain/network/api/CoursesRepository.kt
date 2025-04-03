package com.pivnoydevelopment.onlineschool.common.domain.network.api

import com.pivnoydevelopment.onlineschool.common.domain.models.Course
import com.pivnoydevelopment.onlineschool.common.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CoursesRepository {
    fun fetchCourses(id: String): Flow<Resource<List<Course>>>
}