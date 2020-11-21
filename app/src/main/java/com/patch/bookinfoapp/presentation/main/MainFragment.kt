package com.patch.bookinfoapp.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.patch.bookinfoapp.R
import com.patch.bookinfoapp.common.base.BaseFragment
import com.patch.bookinfoapp.common.util.hideKeyboard
import com.patch.bookinfoapp.databinding.FragmentMainBinding
import com.patch.bookinfoapp.presentation.MainActivityViewModel
import com.patch.bookinfoapp.presentation.detail.DetailFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview


@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>() {
    override val layoutResId: Int = R.layout.fragment_main
    override val viewModel by viewModels<MainViewModel>()
    private val mainViewModel: MainActivityViewModel by activityViewModels()

    private val adapter = BookAdapter { data, index ->
        hideKeyboard(binding.root)
        parentFragmentManager.commit(true) {
            addToBackStack(null)
            add(R.id.container,  DetailFragment.newInstance(data, index))
        }
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchLayout.setOnTextChangedListener { query ->
            viewModel.sendQueryString(query.toString())
        }

        binding.rvBookList.adapter = adapter

        mainViewModel.bookLikeIndex.observe(viewLifecycleOwner, Observer {
            //paged list item의 직접 update는 불가능
            viewModel.setLikeStatus(it)
            adapter.submitList(viewModel.bookLiveData.value)
            adapter.notifyItemChanged(it)
        })
    }

    override fun onPause() {
        binding.searchLayout.setFocus(false)
        super.onPause()
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}