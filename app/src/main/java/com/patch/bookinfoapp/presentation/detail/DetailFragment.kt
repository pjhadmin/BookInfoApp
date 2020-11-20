package com.patch.bookinfoapp.presentation.detail

import android.os.Bundle
import android.view.View
import com.patch.bookinfoapp.R
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.google.android.material.appbar.AppBarLayout
import com.patch.bookinfoapp.common.base.BaseFragment
import com.patch.bookinfoapp.common.util.roundTo2DecimalPlaces
import com.patch.bookinfoapp.databinding.FragmentDetailBinding
import com.patch.bookinfoapp.domain.entity.BookEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs


@AndroidEntryPoint
class DetailFragment: BaseFragment<FragmentDetailBinding>(), AppBarLayout.OnOffsetChangedListener {
    override val layoutResId: Int = R.layout.fragment_detail
    override val viewModel by viewModels<DetailViewModel>()
//    val mainViewModel: MainViewModel by viewModels (ownerProducer = {requireParentFragment()})

    val data: BookEntity.Book? by lazy {
        arguments?.getParcelable<BookEntity.Book>(DETAIL_BOOK_ITEM)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setBookDetailData(data)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.appbar.addOnOffsetChangedListener(this)
        binding.tbToolbar.setNavigationOnClickListener {
            parentFragmentManager.popBackStackImmediate()
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

        fun newInstance(book: BookEntity.Book): DetailFragment {
            return DetailFragment().apply {
                arguments = bundleOf(
                    DETAIL_BOOK_ITEM to book)
            }
        }
    }
}