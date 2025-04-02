package com.pivnoydevelopment.onlineschool.search.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pivnoydevelopment.onlineschool.common.domain.api.CoursesInteractor
import com.pivnoydevelopment.onlineschool.common.domain.models.Course
import kotlinx.coroutines.launch

class SearchViewModel(private val coursesInteractor: CoursesInteractor) : ViewModel() {

    fun loadCourses() {
//        renderState(CoursesState.Loading)

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

        Log.d("LoadedCourses", courses.toString())
/*
        when {
            errorMessage != null -> {
                renderState(
                    CoursesState.Error(
                        errorMessage = context.getString(R.string.something_went_wrong)
                    )
                )
                showToast.postValue(errorMessage!!)
            }

            courses.isEmpty() -> {
                renderState(
                    CoursesState.Empty(
                        message = context.getString(R.string.nothing_found)
                    )
                )
            }

            else -> {
                renderState(
                    CoursesState.Content(
                        courses = courses
                    )
                )
            }
        }
*/
    }
}