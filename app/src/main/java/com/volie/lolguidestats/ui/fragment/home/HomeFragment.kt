package com.volie.lolguidestats.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.volie.lolguidestats.R
import com.volie.lolguidestats.databinding.FragmentHomeBinding
import com.volie.lolguidestats.helper.Status
import com.volie.lolguidestats.ui.adapter.BaseViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _mBinding: FragmentHomeBinding? = null
    private val mBinding get() = _mBinding!!
    private val mViewModel: HomeViewModel by viewModels()
    private val mAdapter: ChampRVAdapter by lazy {
        ChampRVAdapter {
            val action = HomeFragmentDirections.actionHomeFragmentToChampionsDetailsFragment(it)
            findNavController().navigate(action)
        }
    }
    private val fragment = ArrayList<Fragment>()
    private lateinit var tabLayoutMediator: TabLayoutMediator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentHomeBinding.inflate(inflater, container, false)
        mBinding.rvChamp.adapter = mAdapter
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel.getChamp()
        observeLiveData()
        showChampClasses()
    }

    private fun showChampClasses() {

        with(fragment) {
            add(
                ChampClassesFragment(
                    "Controller",
                    getString(R.string.controller_plain)
                )
            )
            add(
                ChampClassesFragment(
                    "Fighter",
                    getString(R.string.fighter_plain)
                )
            )
            add(
                ChampClassesFragment(
                    "Mage",
                    getString(R.string.mage_plain)
                )
            )
            add(
                ChampClassesFragment(
                    "Marksman",
                    getString(R.string.marksman_plain)
                )
            )
            add(
                ChampClassesFragment(
                    "Slayer",
                    getString(R.string.slayer_plain)
                )
            )
            add(
                ChampClassesFragment(
                    "Tank",
                    getString(R.string.tank_plain)
                )
            )
        }

        val champClassesViewPagerAdapter = BaseViewPagerAdapter(fragment, requireActivity())
        mBinding.viewPagerChampClasses.adapter = champClassesViewPagerAdapter

        tabLayoutMediator = TabLayoutMediator(
            mBinding.tabLayoutChampClasses, mBinding.viewPagerChampClasses
        ) { tab, position ->
            when (position) {
                0 -> tab.icon =
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_controller)

                1 -> tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_fighter)
                2 -> tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_mage)
                3 -> tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_marksman)
                4 -> tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_slayer)
                5 -> tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_tank)
            }
        }
        tabLayoutMediator.attach()
    }

    private fun observeLiveData() {
        mViewModel.champ.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { }
                    mAdapter.submitList(it.data?.data?.values?.toList())
                }

                Status.ERROR -> {}
                Status.LOADING -> {}
            }
        }
    }

    override fun onPause() {
        fragment.clear()
        tabLayoutMediator.detach()
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}