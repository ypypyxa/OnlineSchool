package com.pivnoydevelopment.onlineschool.common.ui

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pivnoydevelopment.onlineschool.R
import com.pivnoydevelopment.onlineschool.common.domain.models.Course

class CoursesViewHolder(
    parent: ViewGroup,
    private val clickListener: CoursesAdapter.CoursesClickListener
) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_course, parent, false)
        ) {

    var title: TextView = itemView.findViewById(R.id.title)
    var description: TextView = itemView.findViewById(R.id.description)
    var price: TextView = itemView.findViewById(R.id.price)
    var inFavoriteToggle: ImageView = itemView.findViewById(R.id.bookmarkButton)
    var rate: TextView = itemView.findViewById(R.id.rating)
    var date: TextView = itemView.findViewById(R.id.date)
    var more: TextView = itemView.findViewById(R.id.more)

    fun bind(course: Course) {

        title.text = course.title
        description.text = course.text
        price.text = course.price
        rate.text = course.rate
        date.text = course.startDate

        inFavoriteToggle.setImageDrawable(getFavoriteToggleDrawable(course.hasLike))
        inFavoriteToggle.setOnClickListener { clickListener.onFavoriteToggleClick(course) }

        more.text = itemView.context.getString(R.string.read_more)
        more.setOnClickListener { clickListener.onDetailsClick() }
    }

    private fun getFavoriteToggleDrawable(inFavorite: Boolean): Drawable? {
        return itemView.context.getDrawable(
            if (inFavorite) R.drawable.ic_bookmark_small_fill
                else R.drawable.ic_bookmark_small
        )
    }
}