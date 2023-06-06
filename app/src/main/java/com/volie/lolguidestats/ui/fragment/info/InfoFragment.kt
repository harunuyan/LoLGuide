package com.volie.lolguidestats.ui.fragment.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.volie.lolguidestats.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {
    private var _mBinding: FragmentInfoBinding? = null
    private val mBinding get() = _mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentInfoBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.ivSummonerSpell.setOnClickListener {
            val action = InfoFragmentDirections.actionInfoFragmentToSummonerSpellFragment()
            findNavController().navigate(action)
        }

        mBinding.ivItems.setOnClickListener {
            val action = InfoFragmentDirections.actionInfoFragmentToItemFragment()
            findNavController().navigate(action)
        }

        mBinding.ivProfileIcon.setOnClickListener {
            val action = InfoFragmentDirections.actionInfoFragmentToProfileIconFragment()
            findNavController().navigate(action)
        }

        mBinding.ivRanks.setOnClickListener {
            val action = InfoFragmentDirections.actionInfoFragmentToRankFragment()
            findNavController().navigate(action)
        }

        mBinding.ivMaps.setOnClickListener {
            val action = InfoFragmentDirections.actionInfoFragmentToMapsFragment()
            findNavController().navigate(action)
        }

        mBinding.ivGameModes.setOnClickListener {
            val action = InfoFragmentDirections.actionInfoFragmentToGameModeFragment()
            findNavController().navigate(action)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}