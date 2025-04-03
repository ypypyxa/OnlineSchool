package com.pivnoydevelopment.onlineschool.common.domain.db.api

import com.pivnoydevelopment.onlineschool.common.domain.models.Course
import kotlinx.coroutines.flow.Flow

interface FavoritesCoursesRepository {
    fun loadCourses(): Flow<List<Course>>
    suspend fun loadCoursesOnce(): List<Course>

    suspend fun addCourse(course: Course)
    suspend fun deleteCourse(course: Course)
}