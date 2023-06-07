package com.volie.lolguidestats.ui.fragment.rank_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.volie.lolguidestats.databinding.FragmentRankDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RankDetailsFragment : Fragment() {
    private var _mBinding: FragmentRankDetailsBinding? = null
    private val mBinding get() = _mBinding!!
    private val mAdapter = RankDetailsRVAdapter()
    private val mArgs: RankDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentRankDetailsBinding.inflate(inflater, container, false)
        mBinding.rvRanks.adapter = mAdapter
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.flBack.setOnClickListener {
            findNavController().navigateUp()
        }

        mAdapter.submitList(mArgs.rank.rank)
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}