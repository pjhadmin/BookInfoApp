package com.patch.bookinfoapp.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.patch.bookinfoapp.R
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.google.android.material.appbar.AppBarLayout
import com.patch.bookinfoapp.BR
import com.patch.bookinfoapp.common.extension.toggleAnimation
import com.patch.bookinfoapp.common.util.roundTo2DecimalPlaces
import com.patch.bookinfoapp.databinding.FragmentDetailBinding
import com.patch.bookinfoapp.domain.entity.BookEntity
import com.patch.bookinfoapp.presentation.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs


@AndroidEntryPoint
class DetailFragment: Fragment(), AppBarLayout.OnOffsetChangedListener {
    private val viewModel by viewModels<DetailViewModel>()
    private lateinit var binding: FragmentDetailBinding
    private val mainViewModel: MainActivityViewModel by activityViewModels()

    private val dataIndex: Int? by lazy { arguments?.getInt(DETAIL_ITEM_INDEX) }
    private val data: BookEntity.Book? by lazy { arguments?.getParcelable(DETAIL_BOOK_ITEM) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setBookDetailData(data)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        binding.run {
            lifecycleOwner = viewLifecycleOwner
            setVariable(BR.viewmodel, viewModel)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            appbar.addOnOffsetChangedListener(this@DetailFragment)
            tbToolbar.setNavigationOnClickListener {
                parentFragmentManager.popBackStackImmediate()
            }

            vLike.setOnClickListener {
                vLike.toggleAnimation()
                mainViewModel.bookLikeIndex.setValue(dataIndex)
            }

            urlLinkview.articleColor = ContextCompat.getColor(requireContext(), R.color.colorPrimary)
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
        const val DETAIL_ITEM_INDEX = "detail_item_index"

        fun newInstance(book: BookEntity.Book, itemIndex: Int): DetailFragment {
            return DetailFragment().apply {
                arguments = bundleOf(
                    DETAIL_BOOK_ITEM to book,
                    DETAIL_ITEM_INDEX to itemIndex)
            }
        }
    }
}