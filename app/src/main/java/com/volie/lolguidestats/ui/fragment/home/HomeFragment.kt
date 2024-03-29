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
import com.volie.lolguidestats.helper.SharedPreferenceUtil
import com.volie.lolguidestats.helper.Status
import com.volie.lolguidestats.ui.adapter.BaseViewPagerAdapter
import com.volie.lolguidestats.ui.viewmodel.home_vm.HomeViewModel
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
    private val mSharedPreferenceUtil: SharedPreferenceUtil by lazy {
        SharedPreferenceUtil(requireContext())
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

        mBinding.ivSelectLanguage.setOnClickListener {
            mSharedPreferenceUtil.deleteSelectedItem()
            val action = HomeFragmentDirections.actionHomeFragmentToSelectLanguageFragment()
            findNavController().navigate(action)
        }

        when (mSharedPreferenceUtil.getSelectedItem()) {
            "cs_CZ" -> mBinding.tvLanguage.text = "Czech (Czech Republic)"
            "el_GR" -> mBinding.tvLanguage.text = "Greek (Greece)"
            "pl_PL" -> mBinding.tvLanguage.text = "Polish (Poland)"
            "ro_RO" -> mBinding.tvLanguage.text = "Romanian (Romania)"
            "hu_HU" -> mBinding.tvLanguage.text = "Hungarian (Hungary)"
            "en_GB" -> mBinding.tvLanguage.text = "English (United Kingdom)"
            "de_DE" -> mBinding.tvLanguage.text = "German (Germany)"
            "es_ES" -> mBinding.tvLanguage.text = "Spanish (Spain)"
            "it_IT" -> mBinding.tvLanguage.text = "Italian (Italy)"
            "fr_FR" -> mBinding.tvLanguage.text = "French (France)"
            "ja_JP" -> mBinding.tvLanguage.text = "Japanese (Japan)"
            "ko_KR" -> mBinding.tvLanguage.text = "Korean (Korea)"
            "es_MX" -> mBinding.tvLanguage.text = "Spanish (Mexico)"
            "es_AR" -> mBinding.tvLanguage.text = "Spanish (Argentina)"
            "pt_BR" -> mBinding.tvLanguage.text = "Portuguese (Brazil)"
            "en_US" -> mBinding.tvLanguage.text = "English (United States)"
            "en_AU" -> mBinding.tvLanguage.text = "English (Australia)"
            "ru_RU" -> mBinding.tvLanguage.text = "Russian (Russia)"
            "tr_TR" -> mBinding.tvLanguage.text = "Turkish (Turkey)"
            "ms_MY" -> mBinding.tvLanguage.text = "Malay (Malaysia)"
            "en_PH" -> mBinding.tvLanguage.text = "English (Republic of the Philippines)"
            "en_SG" -> mBinding.tvLanguage.text = "English (Singapore)"
            "th_TH" -> mBinding.tvLanguage.text = "Thai (Thailand)"
            "vi_VN" -> mBinding.tvLanguage.text = "Vietnamese (Viet Nam)"
            "id_ID" -> mBinding.tvLanguage.text = "Indonesian (Indonesia)"
            "zh_MY" -> mBinding.tvLanguage.text = "Chinese (Malaysia)"
            "zh_CN" -> mBinding.tvLanguage.text = "Chinese (China)"
            "zh_TW" -> mBinding.tvLanguage.text = "Chinese (Taiwan)"
            else -> mBinding.tvLanguage.text = ""
        }


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

    override fun onResume() {
        super.onResume()
        if (!tabLayoutMediator.isAttached) {
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
            tabLayoutMediator.attach()
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