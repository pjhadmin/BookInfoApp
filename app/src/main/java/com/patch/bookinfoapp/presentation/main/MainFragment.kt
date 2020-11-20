package com.patch.bookinfoapp.presentation.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.patch.bookinfoapp.R
import com.patch.bookinfoapp.common.base.BaseFragment
import com.patch.bookinfoapp.common.util.hideKeyboard
import com.patch.bookinfoapp.databinding.FragmentMainBinding
import com.patch.bookinfoapp.presentation.detail.DetailFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview


@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>() {

    override val layoutResId: Int = R.layout.fragment_main
    override val viewModel by viewModels<MainViewModel>()

    private val adapter = BookAdapter { data, sharedImageView ->
        hideKeyboard(binding.root)
        parentFragmentManager.commit(true) {
            addToBackStack(null)
            addSharedElement(sharedImageView, sharedImageView.transitionName)
            replace(R.id.container,  DetailFragment.newInstance(data, sharedImageView.transitionName))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchLayout.setOnTextChangedListener { query ->
            viewModel.sendQueryString(query.toString())
        }
        binding.rvBookList.adapter = adapter

        // BaseFragment에 lifeCycleOwner를 지정하지 않을 경우
//        viewModel.bookLiveData.observe(viewLifecycleOwner, Observer{
//            viewModel.isListEmpty.postValue(it.isEmpty())
//            adapter.submitList(it)
//        })
    }

    override fun onResume() {
        super.onResume()
        binding.searchLayout.setFocus(true)
    }

    override fun onPause() {
        binding.searchLayout.setFocus(false)
        super.onPause()
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}