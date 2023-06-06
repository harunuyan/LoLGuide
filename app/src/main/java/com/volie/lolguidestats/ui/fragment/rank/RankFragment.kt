package com.volie.lolguidestats.ui.fragment.rank

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.volie.lolguidestats.databinding.FragmentRankBinding
import com.volie.lolguidestats.helper.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RankFragment : Fragment() {
    private var _mBinding: FragmentRankBinding? = null
    private val mBinding get() = _mBinding!!
    private val mViewModel: RankViewModel by viewModels()
    private val mAdapter: RankRVAdapter by lazy {
        RankRVAdapter {
            val action = RankFragmentDirections.actionRankFragmentToRankDetailsFragment(it)
            findNavController().navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentRankBinding.inflate(inflater, container, false)
        mBinding.rvSeasons.adapter = mAdapter
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeLiveData()
        mViewModel.getRanks()
    }

    private fun observeLiveData() {
        mViewModel.ranks.observe(viewLifecycleOwner) { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    resource.data?.let {
                        mAdapter.submitList(it.data.season)
                    }
                }

                Status.ERROR -> {}
                Status.LOADING -> {}
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _mBinding = null
    }
}