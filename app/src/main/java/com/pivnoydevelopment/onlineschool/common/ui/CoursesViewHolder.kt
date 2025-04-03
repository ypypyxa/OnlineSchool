package com.pivnoydevelopment.onlineschool.common.ui

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pivnoydevelopment.onlineschool.R
import com.pivnoydevelopment.common.domain.models.Course
import com.pivnoydevelopment.common.utils.DateConverter

class CoursesViewHolder(
    parent: ViewGroup,
    private val clickListener: CoursesAdapter.CoursesClickListener
) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_course, parent, false)
        ) {
    companion object {
        private const val CURRENCY = " ₽"
    }

    var title: TextView = itemView.findViewById(R.id.title)
    var description: TextView = itemView.findViewById(R.id.description)
    var price: TextView = itemView.findViewById(R.id.price)
    var inFavoriteToggle: ImageView = itemView.findViewById(R.id.bookmarkButton)
    var rate: TextView = itemView.findViewById(R.id.rating)
    var date: TextView = itemView.findViewById(R.id.date)
    var more: TextView = itemView.findViewById(R.id.more)

    var banner: ImageView = itemView.findViewById(R.id.imageBanner)

    fun bind(course: Course) {

        title.text = course.title
        description.text = course.text
        val priceWithCurrency = course.price + CURRENCY
        price.text = (priceWithCurrency)
        rate.text = course.rate
        val correctedDate = DateConverter.convertDate(course.startDate)
        date.text = correctedDate

        when (course.id) {
            100 -> banner.setImageDrawable(itemView.context.getDrawable(R.drawable.img_banner_java))
            101 -> banner.setImageDrawable(itemView.context.getDrawable(R.drawable.img_banner_three_d_generalist))
            102 -> banner.setImageDrawable(itemView.context.getDrawable(R.drawable.img_banner_python))
            103 -> banner.setImageDrawable(itemView.context.getDrawable(R.drawable.img_banner_system_analyst))
            else -> banner.setImageDrawable(itemView.context.getDrawable(R.drawable.img_banner_data_analyst))
        }

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