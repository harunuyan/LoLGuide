package com.volie.lolguidestats.ui.fragment.champion

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
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
            flSearch.setOnClickListener {
                if (etSearch.isVisible) {
                    val closeKeyboard =
                        requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    closeKeyboard.hideSoftInputFromWindow(etSearch.windowToken, 0)

                    etSearch.text = null

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
                        val color = ContextCompat.getColor(requireContext(), R.color.lol_blue)
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

                val buttons = listOf(
                    Pair(flAll, "Champions"),
                    Pair(flFighter, "Fighter"),
                    Pair(flTank, "Tank"),
                    Pair(flMage, "Mage"),
                    Pair(flAssassin, "Assassin"),
                    Pair(flMarksman, "Marksman"),
                    Pair(flSupport, "Support")
                )

                buttons.forEach { (button, title) ->
                    button.setOnClickListener {
                        mBinding.tvChampionsFeedTitle.text = title

                        when (title) {
                            "Champions" -> mAdapter.submitList(champions)
                            else -> {
                                mAdapter.submitList(filterChampionsByTag(champions, title))
                            }
                        }
                        bottomSheetDialog.dismiss()
                    }
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