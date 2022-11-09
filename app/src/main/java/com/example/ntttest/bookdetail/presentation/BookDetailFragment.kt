package com.example.ntttest.bookdetail.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.ntttest.R
import com.example.ntttest.bookdetail.domain.model.BookDetail
import com.example.ntttest.databinding.FragmentBookDetailBinding
import com.example.ntttest.utils.isWithinThisWeek
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormat

@AndroidEntryPoint
class BookDetailFragment : Fragment() {

    private var binding: FragmentBookDetailBinding? = null

    private val viewModel: BookDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookDetailBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getInt(BOOK_DETAIL_ID_ARGS, -1) ?: -1

        observeData()
        viewModel.loadBookDetail(id)
    }

    private fun observeData() {
        viewModel.bookDetail.observe(viewLifecycleOwner) { book ->
            binding?.apply {
                Glide.with(requireContext())
                    .load("https://dummyimage.com/600x400/000/fff&text=${book.imageText}")
                    .into(imageBook)

                bookTitle.text = book.title
                bookDescription.text = book.description
                bookAuthor.text = book.author
                bookPrice.text = getString(
                    R.string.price_placeholder,
                    DecimalFormat("#,###").format(book.price)
                )

                voteRating.rating = book.rating
                ratingText.text = book.rating.toString()

                showReviewCountIfAny(book)
                bestSellerLabel.isVisible = book.isBestSeller()
                hotLabel.isVisible = book.isHotLesson()
                newLabel.isVisible = book.isNewRelease()

                toggleViewFavorite(book.isInWishList)
                btnAddFavorite.setOnClickListener {
                    viewModel.toggleWishlist()
                }
            }
        }
    }

    private fun toggleViewFavorite(isInWishList: Boolean) {
        binding?.apply {
            if (isInWishList) {
                btnAddFavorite.text = getString(R.string.remove_from_wishlist_label)
                btnAddFavorite.setIconResource(R.drawable.ic_unfavorite)
            } else {
                btnAddFavorite.text = getString(R.string.add_to_wishlist_label)
                btnAddFavorite.setIconResource(R.drawable.ic_favorite)
            }
        }
    }

    private fun BookDetail.isBestSeller(): Boolean {
        return reviewCount > 20 && rating > 4
    }

    private fun BookDetail.isNewRelease(): Boolean {
        return createdDate.isWithinThisWeek()
    }

    private fun BookDetail.isHotLesson(): Boolean {
        return isBestSeller() && isNewRelease()
    }

    private fun showReviewCountIfAny(bookDetail: BookDetail) {
        binding?.apply {
            if (bookDetail.reviewCount > 0) {
                bookReviewCount.text =
                    getString(R.string.review_placeholder, bookDetail.reviewCount)
            } else {
                bookReviewCount.text = getString(R.string.no_review_label)
            }
        }
    }

    companion object {
        const val BOOK_DETAIL_ID_ARGS = "book.detail.id"
    }
}
