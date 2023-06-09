package com.volie.lolguidestats.ui.fragment.mode_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.volie.lolguidestats.databinding.FragmentModeDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ModeDetailsFragment : Fragment() {
    private var _mBinding: FragmentModeDetailsBinding? = null
    private val mBinding get() = _mBinding!!
    private val mArgs: ModeDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentModeDetailsBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.flBack.setOnClickListener {
            findNavController().navigateUp()
        }

        showDetails()
    }

    private fun showDetails() {

        with(mBinding) {
            Glide.with(requireContext())
                .load(mArgs.modeDetails.url)
                .into(ivGameMode)

            tvGameModeTitle.text = mArgs.modeDetails.gameMode
            tvGameModeName.text = mArgs.modeDetails.gameMode
            tvGameModeDescription.text = mArgs.modeDetails.description
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}