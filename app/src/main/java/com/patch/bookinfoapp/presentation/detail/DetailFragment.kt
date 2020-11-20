package com.patch.bookinfoapp.presentation.detail

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import com.patch.bookinfoapp.R
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.google.android.material.appbar.AppBarLayout
import com.patch.bookinfoapp.common.base.BaseFragment
import com.patch.bookinfoapp.common.util.roundTo2DecimalPlaces
import com.patch.bookinfoapp.databinding.FragmentDetailBinding
import com.patch.bookinfoapp.domain.entity.BookEntity
import com.patch.bookinfoapp.presentation.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs


@AndroidEntryPoint
class DetailFragment: BaseFragment<FragmentDetailBinding>(), AppBarLayout.OnOffsetChangedListener {
    override val layoutResId: Int = R.layout.fragment_detail
    override val viewModel by viewModels<DetailViewModel>()
    val mainViewModel: MainViewModel by viewModels (ownerProducer = {requireParentFragment()})

    var isAnimated = false

    val data: BookEntity.Book? by lazy {
        arguments?.getParcelable<BookEntity.Book>(DETAIL_BOOK_ITEM)
    }

    private val transitionName: String? by lazy {
        arguments?.getString(DETAIL_TRANSITION_NAME, "")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        viewModel.setBookDetailData(data)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            ivBookThumbnail.transitionName = transitionName ?: ""
            appbar.addOnOffsetChangedListener(this@DetailFragment)
            tbToolbar.setNavigationOnClickListener {
                parentFragmentManager.popBackStackImmediate()
            }

            vLike.setOnClickListener {
                if (isAnimated) {
                    isAnimated = false
                    this.vLike.speed = -1F
                } else {
                    isAnimated = true
                    this.vLike.speed = 1F
                }
                this.vLike.playAnimation()
            }
        }
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        appBarLayout?.run {
            val offset = abs(verticalOffset) / binding.collapsingLayout.height.toFloat()
            val revOffset = (1.0 - offset).roundTo2DecimalPlaces()
            if(revOffset <= 0.3) {
                binding.collapseContent.alpha = 0.0f
            } else {
                binding.collapseContent.alpha = 1.0f - offset
            }
            binding.tvBarTitle.alpha = offset
        }
    }

    companion object {
        const val DETAIL_BOOK_ITEM = "detail_book_item"
        const val DETAIL_TRANSITION_NAME = "detail_transition_name"

        fun newInstance(book: BookEntity.Book, transitionName: String): DetailFragment {
            return DetailFragment().apply {
                arguments = bundleOf(
                    DETAIL_BOOK_ITEM to book,
                    DETAIL_TRANSITION_NAME to transitionName)
            }
        }
    }
}