package com.pivnoydevelopment.common.domain.db.api

import com.pivnoydevelopment.common.domain.models.Course
import kotlinx.coroutines.flow.Flow

interface FavoritesCoursesInteractor {
    fun loadCourses(): Flow<List<Course>>
    suspend fun loadCoursesOnce(): List<Course>

    suspend fun addCourse(course: Course)
    suspend fun deleteCourse(course: Course)
}