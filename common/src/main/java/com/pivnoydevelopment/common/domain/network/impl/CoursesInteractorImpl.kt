package com.pivnoydevelopment.common.domain.network.impl

import com.pivnoydevelopment.common.domain.network.api.CoursesInteractor
import com.pivnoydevelopment.common.domain.network.api.CoursesRepository
import com.pivnoydevelopment.common.domain.models.Course
import com.pivnoydevelopment.common.utils.Resource
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