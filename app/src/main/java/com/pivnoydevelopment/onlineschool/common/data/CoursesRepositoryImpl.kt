package com.pivnoydevelopment.onlineschool.common.data

import com.pivnoydevelopment.onlineschool.common.data.dto.CoursesRequest
import com.pivnoydevelopment.onlineschool.common.data.dto.CoursesResponse
import com.pivnoydevelopment.onlineschool.common.domain.api.CoursesRepository
import com.pivnoydevelopment.onlineschool.common.domain.models.Course
import com.pivnoydevelopment.onlineschool.common.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CoursesRepositoryImpl(private val networkClient: NetworkClient) : CoursesRepository {

    override fun fetchCourses(id: String): Flow<Resource<List<Course>>> = flow {
        val response = networkClient.doRequest(CoursesRequest(id))
        when (response.resultCode) {
            -1 -> {
                emit(Resource.Error("Проверьте подключение к интернету"))
            }
            200 -> {
//                val stored = localStorage.getSavedFavorites()
                with (response as CoursesResponse) {
                    val data = courses.map {
                        Course(
                            id = it.id,
                            title = it.title,
                            text = it.text,
                            price = it.price,
                            rate = it.rate,
                            startDate = it.startDate,
                            hasLike = it.hasLike,
                            publishDate = it.publishDate
//                            inFavorite = stored.contains(it.id)
                        )
                    }
                    //сохраняем список фильмов в базу данных
                    emit(Resource.Success(data))
                }
            }
            else -> {
                emit(Resource.Error("Ошибка сервера"))
            }
        }
    }

/*
    override fun addMovieToFavorites(course: Course) {
        localStorage.addToFavorites(course.id)
    }

    override fun removeMovieFromFavorites(course: Course) {
        localStorage.removeFromFavorites(course.id)
    }
*/
}