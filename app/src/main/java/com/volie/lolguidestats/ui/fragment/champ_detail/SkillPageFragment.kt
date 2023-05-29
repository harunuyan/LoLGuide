package com.volie.lolguidestats.ui.fragment.champ_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.volie.lolguidestats.databinding.FragmentSkillPageBinding

class SkillPageFragment(private val skillName: String, private val skillDescription: String) :
    Fragment() {
    private var _mBinding: FragmentSkillPageBinding? = null
    private val mBinding get() = _mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentSkillPageBinding.inflate(inflater, container, false)
        mBinding.tvSkillName.text = skillName
        mBinding.tvSkillDescription.text = skillDescription
        return mBinding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}