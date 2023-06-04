package com.volie.lolguidestats.ui.fragment.icon_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.volie.lolguidestats.databinding.FragmentProfileIconDetailsBinding
import com.volie.lolguidestats.helper.Constant.CHAMPION_IMAGE_URL
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileIconDetailsFragment : Fragment() {
    private var _mBinding: FragmentProfileIconDetailsBinding? = null
    private val mBinding get() = _mBinding!!
    private val mArgs: ProfileIconDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentProfileIconDetailsBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }

        showDetails()
    }

    private fun showDetails() {
        Glide.with(requireContext())
            .load("${CHAMPION_IMAGE_URL}profileicon/${mArgs.itemImage}")
            .into(mBinding.ivProfileIcon)
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}