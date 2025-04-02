package com.pivnoydevelopment.onlineschool.search.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pivnoydevelopment.onlineschool.common.domain.api.CoursesInteractor
import com.pivnoydevelopment.onlineschool.common.domain.models.Course
import com.pivnoydevelopment.onlineschool.common.utils.SingleLiveEvent
import com.pivnoydevelopment.onlineschool.search.ui.model.CoursesFragmentState
import kotlinx.coroutines.launch

class CoursesViewModel(
    private val coursesInteractor: CoursesInteractor,
) : ViewModel() {

//    init {
//        loadCourses()
//    }

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
            }
        }
    }

    private fun renderState(state: CoursesFragmentState) {
        _stateLiveData.postValue(state)
    }

    fun loadCourses() {
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
                renderState(
                    CoursesFragmentState.Error(
                        errorMessage = "ошибка" // context.getString(R.string.something_went_wrong)
                    )
                )
                _showToast.postValue(errorMessage!!)
            }

            courses.isEmpty() -> {
                renderState(
                    CoursesFragmentState.Empty(
                        message = "пусто" // context.getString(R.string.nothing_found)
                    )
                )
            }

            else -> {
                renderState(
                    CoursesFragmentState.Content(
                        courses = courses
                    )
                )
            }
        }
    }
}