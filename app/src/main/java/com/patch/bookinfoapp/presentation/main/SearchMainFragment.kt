package com.patch.bookinfoapp.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.patch.bookinfoapp.BR
import com.patch.bookinfoapp.R
import com.patch.bookinfoapp.common.corourine.StateCoroutine
import com.patch.bookinfoapp.common.corourine.StateCoroutineImpl
import com.patch.bookinfoapp.common.util.hideKeyboard
import com.patch.bookinfoapp.databinding.FragmentMainBinding
import com.patch.bookinfoapp.presentation.MainActivityViewModel
import com.patch.bookinfoapp.presentation.detail.DetailFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchMainFragment : Fragment(), StateCoroutine by StateCoroutineImpl() {
    private val viewModel by viewModels<SearchMainViewModel>()
    private val mainViewModel: MainActivityViewModel by activityViewModels()
    private lateinit var binding: FragmentMainBinding

    private val adapter = BookAdapter { data, index ->
        hideKeyboard(binding.root)
        parentFragmentManager.commit(true) {
            addToBackStack(null)
            add(R.id.container,  DetailFragment.newInstance(data, index))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.run {
            lifecycleOwner = viewLifecycleOwner
            setVariable(BR.viewmodel, viewModel)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchLayout.setOnTextChangedListener { query ->
            viewModel.searchBookKeyword(query)
        }

        binding.rvBookList.adapter = adapter

        mainViewModel.bookLikeIndex.observe(viewLifecycleOwner, Observer {
            //paged list item의 직접 update는 불가능
            viewModel.setLikeStatus(it)
            adapter.submitList(viewModel.bookLiveData.value)
            adapter.notifyItemChanged(it)
        })

        binding.searchLayout.setFocus(true)

        exception.observe(viewLifecycleOwner, {
            // Error 처리
        })
    }

    override fun onPause() {
        binding.searchLayout.setFocus(false)
        super.onPause()
    }

    companion object {
        fun newInstance() = SearchMainFragment()
    }
}