package com.pivnoydevelopment.onlineschool.common.domain.impl

import com.pivnoydevelopment.onlineschool.common.domain.api.CoursesInteractor
import com.pivnoydevelopment.onlineschool.common.domain.api.CoursesRepository
import com.pivnoydevelopment.onlineschool.common.domain.models.Course
import com.pivnoydevelopment.onlineschool.common.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class CoursesInteractorImpl(private val repository: CoursesRepository) : CoursesInteractor {

    override fun fetchCourses(id: String): Flow<Pair<List<Course>?, String?>> {
        return repository.fetchCourses(id).map { result ->
            when(result) {
                is Resource.Success -> { Pair(result.data, null) }
                is Resource.Error -> { Pair(null, result.message) }
            }
        }
    }

/*
    override fun addMovieToFavorites(course: Course) {
        repository.addMovieToFavorites(course)
    }

    override fun removeMovieFromFavorites(course: Course) {
        repository.removeMovieFromFavorites(course)
    }
*/
}