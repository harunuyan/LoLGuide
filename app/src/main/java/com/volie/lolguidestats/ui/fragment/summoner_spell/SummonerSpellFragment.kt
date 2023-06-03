package com.volie.lolguidestats.ui.fragment.summoner_spell

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.volie.lolguidestats.databinding.FragmentSummonerSpellBinding
import com.volie.lolguidestats.helper.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SummonerSpellFragment : Fragment() {
    private var _mBinding: FragmentSummonerSpellBinding? = null
    private val mBinding get() = _mBinding!!
    private val mAdapter = SummonerSpellRVAdapter()
    private val mViewModel: SummonerSpellViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentSummonerSpellBinding.inflate(inflater, container, false)
        mBinding.rvSummonerSpell.adapter = mAdapter
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeLiveData()
        mViewModel.getSummonerSpell()
    }

    private fun observeLiveData() {
        mViewModel.summonerSpell.observe(viewLifecycleOwner) { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    resource.data?.let {
                        val spellMap = it.data
                        val spellList = spellMap.values.toList()
                        mAdapter.submitList(spellList)
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