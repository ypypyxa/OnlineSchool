package com.pivnoydevelopment.onlineschool.common.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pivnoydevelopment.onlineschool.common.domain.models.Course

class CoursesAdapter(private val clickListener: CoursesClickListener) : RecyclerView.Adapter<CoursesViewHolder>() {

    var courses = ArrayList<Course>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) : CoursesViewHolder = CoursesViewHolder(parent, clickListener)

    override fun onBindViewHolder(holder: CoursesViewHolder, position: Int) {
        holder.bind(courses[position])
    }

    override fun getItemCount(): Int = courses.size

    fun updateList(newCourses: List<Course>) {
        val diffCallback = CoursesDiffCallback(courses, newCourses)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        courses.clear()
        courses.addAll(newCourses)
        diffResult.dispatchUpdatesTo(this)
    }

    interface CoursesClickListener {
        fun onDetailsClick()
        fun onFavoriteToggleClick(course: Course)
    }
}