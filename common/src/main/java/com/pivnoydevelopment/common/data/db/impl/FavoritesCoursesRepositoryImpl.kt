package com.pivnoydevelopment.common.data.db.impl

import com.pivnoydevelopment.common.data.db.database.AppDatabase
import com.pivnoydevelopment.common.domain.db.api.FavoritesCoursesRepository
import com.pivnoydevelopment.common.domain.models.Course
import com.pivnoydevelopment.common.utils.DbConverter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoritesCoursesRepositoryImpl(
    private val database: AppDatabase,
    private val dbConverter: DbConverter) : FavoritesCoursesRepository {

    override fun loadCourses(): Flow<List<Course>> =
        database.favoritesDao().getCourses()
            .map { entites ->
                entites.map { entity -> dbConverter.map(entity) }
            }

    override suspend fun loadCoursesOnce(): List<Course> {
        return database.favoritesDao().getCoursesOnce().map { dbConverter.map(it) }
    }

    override suspend fun addCourse(course: Course) {
        database.favoritesDao().insertNewCourse(dbConverter.map(course))
    }

    override suspend fun deleteCourse(course: Course) {
        database.favoritesDao().deleteCourse(dbConverter.map(course))
    }
}