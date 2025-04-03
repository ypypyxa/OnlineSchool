package com.pivnoydevelopment.onlineschool.common.ui

import androidx.recyclerview.widget.DiffUtil
import com.pivnoydevelopment.onlineschool.common.domain.models.Course

class CoursesDiffCallback(
    private val oldList: List<Course>,
    private val newList: List<Course>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        // Сравнение по уникальному ID
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        // Можно использовать equals(), если data class, или сравнивать вручную
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}