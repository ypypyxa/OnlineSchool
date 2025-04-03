package com.pivnoydevelopment.common.data.network.impl

import com.pivnoydevelopment.common.data.network.NetworkClient
import com.pivnoydevelopment.common.data.network.dto.CoursesRequest
import com.pivnoydevelopment.common.data.network.dto.CoursesResponse
import com.pivnoydevelopment.common.domain.db.api.FavoritesCoursesInteractor
import com.pivnoydevelopment.common.domain.network.api.CoursesRepository
import com.pivnoydevelopment.common.domain.models.Course
import com.pivnoydevelopment.common.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CoursesRepositoryImpl(
    private val networkClient: NetworkClient,
    private val favorites : FavoritesCoursesInteractor
) : CoursesRepository {

    override fun fetchCourses(id: String): Flow<Resource<List<Course>>> = flow {
        val response = networkClient.doRequest(CoursesRequest(id))
        when (response.resultCode) {
            -1 -> {
                emit(Resource.Error("Проверьте подключение к интернету"))
            }
            200 -> {
                val storedIds = favorites.loadCoursesOnce().map { it.id }
                with (response as CoursesResponse) {
                    val data = courses.map {
                        Course(
                            id = it.id,
                            title = it.title,
                            text = it.text,
                            price = it.price,
                            rate = it.rate,
                            startDate = it.startDate,
                            hasLike = storedIds.contains(it.id), //it.hasLike,
                            publishDate = it.publishDate
                        )
                    }
                    emit(Resource.Success(data))
                }
            }
            else -> {
                emit(Resource.Error("Ошибка сервера"))
            }
        }
    }
}