package com.pivnoydevelopment.onlineschool.favorites.ui.model

import com.pivnoydevelopment.common.domain.models.Course

sealed interface FavoritesFragmentState {

    data class Content(
        val courses: List<Course>
    ) : FavoritesFragmentState

    data class Empty(
        val message: String
    ) : FavoritesFragmentState

}