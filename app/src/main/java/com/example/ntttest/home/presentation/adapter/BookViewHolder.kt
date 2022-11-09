package com.example.ntttest.home.presentation.adapter

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.ntttest.R
import com.example.ntttest.databinding.ViewItemBookBinding
import com.example.ntttest.home.domain.model.Book
import com.example.ntttest.utils.isWithinThisWeek
import java.text.DecimalFormat

class BookViewHolder(private val binding: ViewItemBookBinding) : ViewHolder(binding.root) {

    fun bind(book: Book) {
        binding.apply {
            bookTitle.text = book.title
            bookDescription.text = book.description
            bookAuthor.text = book.author
            bookPrice.text = itemView.context.getString(
                R.string.price_placeholder,
                DecimalFormat("#,###").format(book.price)
            )

            voteRating.rating = book.rating

            Glide.with(binding.root.context)
                .load("https://dummyimage.com/600x400/000/fff&text=${book.imageText}")
                .into(binding.imageBook)

            bestSellerLabel.isVisible = book.isBestSeller()
            hotLabel.isVisible = book.isHotLesson()
            newLabel.isVisible = book.isNewRelease()
        }
    }

    private fun Book.isBestSeller(): Boolean {
        return reviewCount > 20 && rating > 4
    }

    private fun Book.isNewRelease(): Boolean {
        return createdDate.isWithinThisWeek()
    }

    private fun Book.isHotLesson(): Boolean {
        return isBestSeller() && isNewRelease()
    }
}
