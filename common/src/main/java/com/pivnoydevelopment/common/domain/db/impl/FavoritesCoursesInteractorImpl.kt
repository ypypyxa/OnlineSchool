package com.pivnoydevelopment.common.domain.db.impl

import com.pivnoydevelopment.common.domain.db.api.FavoritesCoursesInteractor
import com.pivnoydevelopment.common.domain.db.api.FavoritesCoursesRepository
import com.pivnoydevelopment.common.domain.models.Course
import kotlinx.coroutines.flow.Flow

class FavoritesCoursesInteractorImpl(
    private val repository: FavoritesCoursesRepository
) : FavoritesCoursesInteractor {

    override fun loadCourses(): Flow<List<Course>> {
        return repository.loadCourses()
    }

    override suspend fun loadCoursesOnce(): List<Course> {
        return repository.loadCoursesOnce()
    }

    override suspend fun addCourse(course: Course) {
        repository.addCourse(course)
    }

    override suspend fun deleteCourse(course: Course) {
        repository.deleteCourse(course)
    }

}