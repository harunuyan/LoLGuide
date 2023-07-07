package com.volie.lolguidestats.ui.fragment.champ_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.volie.lolguidestats.databinding.FragmentSkillPageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SkillPageFragment(
    private val skillName: String,
    private val skillDescription: String,
    private val skillImage: String
) :
    Fragment() {
    private var _mBinding: FragmentSkillPageBinding? = null
    private val mBinding get() = _mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentSkillPageBinding.inflate(inflater, container, false)
        with(mBinding) {
            tvSkillName.text = skillName
            tvSkillDescription.text = skillDescription
            Glide.with(requireContext())
                .load(skillImage)
                .into(ivSkillImage)
        }

        return mBinding.root
    }
}