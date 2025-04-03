package com.pivnoydevelopment.onlineschool.favorites.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pivnoydevelopment.onlineschool.common.domain.db.api.FavoritesCoursesInteractor
import com.pivnoydevelopment.onlineschool.common.domain.models.Course
import com.pivnoydevelopment.onlineschool.favorites.ui.model.FavoritesFragmentState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val favoritesInteractor: FavoritesCoursesInteractor
) : ViewModel() {

    private val _state = MutableLiveData<FavoritesFragmentState>()
    fun observeState(): LiveData<FavoritesFragmentState> = _state

    init {
        observeFavorites()
    }

    private fun observeFavorites() {

        viewModelScope.launch {
            favoritesInteractor.loadCourses().collectLatest { favorites ->
                if (favorites.isEmpty()) {
                    _state.postValue(FavoritesFragmentState.Empty("Нет избранных курсов"))
                } else {
                    _state.postValue(FavoritesFragmentState.Content(favorites))
                }
            }
        }
    }

    fun removeFromFavorites(course: Course) {
        viewModelScope.launch {
            favoritesInteractor.deleteCourse(course)
        }
    }
}