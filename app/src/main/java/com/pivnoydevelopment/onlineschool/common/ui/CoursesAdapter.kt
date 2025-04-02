package com.pivnoydevelopment.onlineschool.common.ui

import android.view.ViewGroup
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

    interface CoursesClickListener {
        fun onDetailsClick()
        fun onFavoriteToggleClick(course: Course)
    }
}