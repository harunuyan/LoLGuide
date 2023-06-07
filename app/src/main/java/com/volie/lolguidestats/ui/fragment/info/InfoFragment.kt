package com.volie.lolguidestats.ui.fragment.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
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

        Glide.with(requireContext())
            .load("https://strongestgames.com/wp-content/uploads/2022/12/Summoner-Spells.png")
            .into(mBinding.ivSummonerSpell)

        mBinding.ivSummonerSpell.setOnClickListener {
            val action = InfoFragmentDirections.actionInfoFragmentToSummonerSpellFragment()
            findNavController().navigate(action)
        }

        Glide.with(requireContext())
            .load("https://ddragon.leagueoflegends.com/cdn/13.11.1/img/sprite/item0.png")
            .into(mBinding.ivItems)

        mBinding.ivItems.setOnClickListener {
            val action = InfoFragmentDirections.actionInfoFragmentToItemFragment()
            findNavController().navigate(action)
        }

        Glide.with(requireContext())
            .load("https://static.wikia.nocookie.net/leagueoflegends/images/9/9d/Summoner_Icons.png/revision/latest/scale-to-width-down/400?cb=20170310212719")
            .into(mBinding.ivProfileIcon)

        mBinding.ivProfileIcon.setOnClickListener {
            val action = InfoFragmentDirections.actionInfoFragmentToProfileIconFragment()
            findNavController().navigate(action)
        }

        Glide.with(requireContext())
            .load("https://images.mein-mmo.de/medien/2023/02/LoL-Ranks-Titel-780x438.jpg")
            .into(mBinding.ivRanks)

        mBinding.ivRanks.setOnClickListener {
            val action = InfoFragmentDirections.actionInfoFragmentToRankFragment()
            findNavController().navigate(action)
        }

        Glide.with(requireContext())
            .load("https://lh4.googleusercontent.com/gtT7rL889HZLD1mIyLFSfwoWC1DTosyOy-1nnyTl9syFxoyrO3zGErIL69xXr2f5pjhdPpSgDtg0_5VjgDtyHNQW4Ms5PGjCmT7p9Xh7xAq5IWRqC26SKXaQoCBHQyj3G2wyLZEGmfm_xA8DInVqhg")
            .into(mBinding.ivMaps)

        mBinding.ivMaps.setOnClickListener {
            val action = InfoFragmentDirections.actionInfoFragmentToMapsFragment()
            findNavController().navigate(action)
        }

        Glide.with(requireContext())
            .load("https://static.wikia.nocookie.net/leagueoflegends/images/7/7b/FGM_Ultra_Rapid_Fire.jpg/revision/latest?cb=20150320193046")
            .into(mBinding.ivGameModes)

        mBinding.ivGameModes.setOnClickListener {
            val action = InfoFragmentDirections.actionInfoFragmentToGameModeFragment()
            findNavController().navigate(action)
        }

        Glide.with(requireContext())
            .load("https://1.bp.blogspot.com/-TXZfsdm7NoM/WR4Bg0c45lI/AAAAAAAAlhM/m6cqTR3JgIQclAecqA7P7TV-5OqSK2S9gCLcB/s1600/MissionsBanner_1920x1080.jpg")
            .into(mBinding.ivMissionAssets)

        mBinding.ivMissionAssets.setOnClickListener {
            val action = InfoFragmentDirections.actionInfoFragmentToMissionAssetsFragment()
            findNavController().navigate(action)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}