package com.volie.lolguidestats.ui.fragment.champion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.volie.lolguidestats.R
import com.volie.lolguidestats.data.model.champion.Champion
import com.volie.lolguidestats.databinding.BottomSheetChampionClassBinding
import com.volie.lolguidestats.databinding.FragmentChampionBinding
import com.volie.lolguidestats.helper.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
    private var championList: List<Champion> = emptyList()

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

        searchForChampions()

        with(mBinding) {
            ivSearch.setOnClickListener {
                if (etSearch.isVisible) {
                    tvChampionsFeedTitle.visibility = View.VISIBLE
                    etSearch.visibility = View.INVISIBLE
                    ivSearch.setImageResource(R.drawable.ic_search).also {
                        val color = ContextCompat.getColor(requireContext(), R.color.white)
                        ivSearch.setColorFilter(color)
                    }
                } else {
                    tvChampionsFeedTitle.visibility = View.GONE
                    etSearch.visibility = View.VISIBLE
                    ivSearch.setImageResource(R.drawable.ic_champ).also {
                        val color = ContextCompat.getColor(requireContext(), R.color.lol_yellow)
                        ivSearch.setColorFilter(color)
                    }
                }
            }
            rvChampions.adapter = mAdapter
        }

        mViewModel.getChampions()
        observeLiveData()
    }

    private fun setupBottomSheet(champions: List<Champion>) {

        championList = champions

        mBinding.ivSort.setOnClickListener {
            val bottomSheetDialog =
                BottomSheetDialog(requireContext())
            val bottomSheetView = LayoutInflater.from(requireContext())
                .inflate(R.layout.bottom_sheet_champion_class, mBinding.root, false)
            bottomSheetDialog.setContentView(bottomSheetView)
            bottomSheetDialog.show()

            val mView = BottomSheetChampionClassBinding.bind(bottomSheetView)

            with(mView) {
                flAll.setOnClickListener {

                    mBinding.tvChampionsFeedTitle.text = getString(R.string.champions)

                    observeLiveData()

                    bottomSheetDialog.dismiss()
                }
                flFighter.setOnClickListener {

                    mBinding.tvChampionsFeedTitle.text = getString(R.string.fighter)

                    val filteredChampions = filterChampionsByTag(champions, "Fighter")
                    mAdapter.submitList(filteredChampions)

                    bottomSheetDialog.dismiss()
                }
                flTank.setOnClickListener {

                    mBinding.tvChampionsFeedTitle.text = getString(R.string.tank)

                    val filteredChampions = filterChampionsByTag(champions, "Tank")
                    mAdapter.submitList(filteredChampions)

                    bottomSheetDialog.dismiss()
                }
                flMage.setOnClickListener {

                    mBinding.tvChampionsFeedTitle.text = getString(R.string.mage)

                    val filteredChampions = filterChampionsByTag(champions, "Mage")
                    mAdapter.submitList(filteredChampions)

                    bottomSheetDialog.dismiss()
                }
                flAssassin.setOnClickListener {

                    mBinding.tvChampionsFeedTitle.text = getString(R.string.assassin)

                    val filteredChampions = filterChampionsByTag(champions, "Assassin")
                    mAdapter.submitList(filteredChampions)

                    bottomSheetDialog.dismiss()
                }
                flMarksman.setOnClickListener {

                    mBinding.tvChampionsFeedTitle.text = getString(R.string.marksman)

                    val filteredChampions = filterChampionsByTag(champions, "Marksman")
                    mAdapter.submitList(filteredChampions)

                    bottomSheetDialog.dismiss()
                }
                flSupport.setOnClickListener {

                    mBinding.tvChampionsFeedTitle.text = getString(R.string.support)

                    val filteredChampions = filterChampionsByTag(champions, "Support")
                    mAdapter.submitList(filteredChampions)

                    bottomSheetDialog.dismiss()
                }
            }
        }
    }

    private fun observeLiveData() {
        mViewModel.champs.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { data ->
                        val championMap = data.data
                        val championList = championMap.values.toList()
                        mAdapter.submitList(championList)

                        setupBottomSheet(championList)
                    }
                }

                Status.ERROR -> {}
                Status.LOADING -> {}
            }
        }
    }

    private fun filterChampionsByTag(champion: List<Champion>, tag: String): List<Champion> {
        return champion.filter { it.tags?.contains(tag) == true }
    }

    private fun searchForChampions() {
        var job: Job? = null

        mBinding.etSearch.addTextChangedListener { editable ->
            job?.cancel()
            job = lifecycleScope.launch {
                delay(300)
                editable?.toString()?.let { searchQuery ->
                    val filteredChampions = filterChampionsByName(championList, searchQuery)
                    mAdapter.submitList(filteredChampions)
                }
            }
        }
    }

    private fun filterChampionsByName(champions: List<Champion>, name: String): List<Champion> {
        return champions.filter { champion ->
            champion.name?.contains(name, ignoreCase = true) == true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}