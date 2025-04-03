package com.pivnoydevelopment.onlineschool.courses.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pivnoydevelopment.common.domain.db.api.FavoritesCoursesInteractor
import com.pivnoydevelopment.common.domain.models.Course
import com.pivnoydevelopment.common.domain.network.api.CoursesInteractor
import com.pivnoydevelopment.onlineschool.common.utils.SingleLiveEvent
import com.pivnoydevelopment.onlineschool.courses.ui.model.CoursesFragmentState
import kotlinx.coroutines.launch

class CoursesViewModel(
    private val coursesInteractor: CoursesInteractor,
    private val favoritesInteractor: FavoritesCoursesInteractor
) : ViewModel() {

    private var courses = listOf<Course>()
    private var isCoursesLoaded = false

    private val _showToast = SingleLiveEvent<String>()
    fun observeShowToast(): LiveData<String> = _showToast

    private val _stateLiveData = MutableLiveData<CoursesFragmentState>()
    fun observeState(): LiveData<CoursesFragmentState> = mediatorStateLiveData

    private val mediatorStateLiveData = MediatorLiveData<CoursesFragmentState>().also { liveData ->
        liveData.addSource(_stateLiveData) { coursesFragmentState ->
            liveData.value = when (coursesFragmentState) {
                is CoursesFragmentState.Content -> CoursesFragmentState.Content(coursesFragmentState.courses)
                is CoursesFragmentState.Empty -> coursesFragmentState
                is CoursesFragmentState.Error -> coursesFragmentState
                is CoursesFragmentState.Loading -> coursesFragmentState
                is CoursesFragmentState.Sort -> CoursesFragmentState.Sort(coursesFragmentState.courses)
            }
        }
    }

    init {
        loadCourses()
        observeFavoritesUpdates()
    }

    fun sort() {
        renderState(
            CoursesFragmentState.Sort(
                courses = courses.sortedByDescending { it.publishDate }
            )
        )
    }

    private fun renderState(state: CoursesFragmentState) {
        _stateLiveData.postValue(state)
    }

    private fun loadCourses() {
        renderState(CoursesFragmentState.Loading)

        viewModelScope.launch {
            coursesInteractor
                .fetchCourses("15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q")
                .collect { pair ->
                    processResult(pair.first, pair.second)
                }
        }
    }

    private fun processResult(loadedCourses: List<Course>?, errorMessage: String?) {
        val courses = mutableListOf<Course>()
        if (loadedCourses != null) {
            courses.addAll(loadedCourses)
        }

        when {
            errorMessage != null -> {
                renderState(CoursesFragmentState.Error("ошибка"))
                _showToast.postValue(errorMessage!!)
            }

            courses.isEmpty() -> {
                renderState(CoursesFragmentState.Empty("пусто"))
            }

            else -> {
                isCoursesLoaded = true
                this.courses = courses
                renderState(CoursesFragmentState.Content(courses))
            }
        }
    }

    private fun observeFavoritesUpdates() {
        viewModelScope.launch {
            favoritesInteractor.loadCourses().collect { favorites ->
                val favoriteIds = favorites.map { it.id }.toSet()
                courses = courses.map { course ->
                    course.copy(hasLike = course.id in favoriteIds)
                }

                if (isCoursesLoaded) {
                    renderState(CoursesFragmentState.Content(courses))
                }
            }
        }
    }

    fun onFavoriteToggleClick(course: Course) {
        if (course.hasLike) {
            removeFromFavorites(course)
        } else {
            addToFavorites(course)
        }
    }

    private fun addToFavorites(course: Course) {
        viewModelScope.launch {
            favoritesInteractor.addCourse(course)
        }
    }

    private fun removeFromFavorites(course: Course) {
        viewModelScope.launch {
            favoritesInteractor.deleteCourse(course)
        }
    }
}