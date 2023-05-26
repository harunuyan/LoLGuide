package com.volie.lolguidestats.ui.fragment.champion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.volie.lolguidestats.databinding.FragmentChampionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChampionFragment : Fragment() {
    private var _mBinding: FragmentChampionBinding? = null
    private val mBinding get() = _mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentChampionBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}