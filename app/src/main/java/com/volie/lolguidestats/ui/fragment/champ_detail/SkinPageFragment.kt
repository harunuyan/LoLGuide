package com.volie.lolguidestats.ui.fragment.champ_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.volie.lolguidestats.databinding.FragmentSkinPageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SkinPageFragment(private val skinsImage: String) : Fragment() {
    private var _mBinding: FragmentSkinPageBinding? = null
    private val mBinding get() = _mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentSkinPageBinding.inflate(inflater, container, false)
        Glide.with(requireContext())
            .load(skinsImage)
            .into(mBinding.ivSkins)
        return mBinding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}