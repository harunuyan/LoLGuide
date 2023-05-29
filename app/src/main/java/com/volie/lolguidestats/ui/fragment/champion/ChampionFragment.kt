package com.volie.lolguidestats.ui.fragment.champion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.volie.lolguidestats.databinding.FragmentChampionBinding
import com.volie.lolguidestats.helper.Status
import com.volie.lolguidestats.ui.adapter.ChampRVAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChampionFragment : Fragment() {
    private var _mBinding: FragmentChampionBinding? = null
    private val mBinding get() = _mBinding!!
    private val mViewModel: ChampionViewModel by viewModels()
    private val mAdapter: ChampRVAdapter by lazy {
        ChampRVAdapter {
            val action =
                ChampionFragmentDirections.actionChampionFragmentToChampionsDetailsFragment(it)
            findNavController().navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentChampionBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.rvChampions.adapter = mAdapter

        mViewModel.getChampions()
        observeLiveData()
    }

    private fun observeLiveData() {
        mViewModel.champs.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { data ->
                        val championMap = data.data
                        val championList = championMap.values.toList()
                        mAdapter.submitList(championList)
                    }
                }

                Status.ERROR -> {}
                Status.LOADING -> {}
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}