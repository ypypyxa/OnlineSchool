package com.pivnoydevelopment.common.utils

import com.pivnoydevelopment.common.data.db.entity.FavoritesCourseEntity
import com.pivnoydevelopment.common.domain.models.Course

class DbConverter {

    fun map(course: Course): FavoritesCourseEntity {
        return FavoritesCourseEntity(
            id = course.id,
            title = course.title,
            text = course.text,
            price = course.price,
            rate = course.rate,
            startDate = course.startDate,
            hasLike = true,
            publishDate = course.publishDate
        )
    }

    fun map(courseEntity: FavoritesCourseEntity): Course {
        return Course(
            id = courseEntity.id,
            title = courseEntity.title,
            text = courseEntity.text,
            price = courseEntity.price,
            rate = courseEntity.rate,
            startDate = courseEntity.startDate,
            hasLike = courseEntity.hasLike,
            publishDate = courseEntity.publishDate
        )
    }

}