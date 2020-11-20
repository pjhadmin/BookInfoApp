package com.patch.bookinfoapp.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.patch.bookinfoapp.BR
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.patch.bookinfoapp.common.view.ProgressDialog

abstract class BaseFragment<BINDING: ViewDataBinding>: Fragment() {

    protected lateinit var binding: BINDING
    protected abstract val layoutResId: Int
    protected abstract val viewModel: ViewModel

    private val progressDialog by lazy {
        ProgressDialog(binding.root.context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        binding.run {
            lifecycleOwner = viewLifecycleOwner
            setVariable(BR.viewmodel, viewModel)
        }
        return binding.root
    }
}