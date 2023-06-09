package com.volie.lolguidestats.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.volie.lolguidestats.databinding.FragmentChampClassesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChampClassesFragment(
    private val className: String,
    private val classDescription: String
) :
    Fragment() {
    private var _mBinding: FragmentChampClassesBinding? = null
    private val mBinding get() = _mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentChampClassesBinding.inflate(inflater, container, false)
        mBinding.tvClassName.text = className
        mBinding.tvClassDescription.text = classDescription
        return mBinding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}