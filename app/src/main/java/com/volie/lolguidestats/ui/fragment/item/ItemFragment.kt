package com.volie.lolguidestats.ui.fragment.item

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.volie.lolguidestats.R
import com.volie.lolguidestats.data.model.item.Item
import com.volie.lolguidestats.databinding.BottomSheetFilterItemBinding
import com.volie.lolguidestats.databinding.FragmentItemBinding
import com.volie.lolguidestats.helper.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ItemFragment : Fragment() {
    private var _mBinding: FragmentItemBinding? = null
    private val mBinding get() = _mBinding!!
    private val mViewModel: ItemViewModel by viewModels()
    private val mAdapter: ItemRVAdapter by lazy {
        ItemRVAdapter(onItemClick = {
            val action = ItemFragmentDirections.actionItemFragmentToItemDetailsFragment(it)
            findNavController().navigate(action)
        })
    }
    private var itemList: List<Item> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentItemBinding.inflate(inflater, container, false)
        mBinding.rvItems.adapter = mAdapter
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(mBinding) {
            flSearch.setOnClickListener {
                if (etSearch.isVisible) {
                    val closeKeyboard =
                        requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    closeKeyboard.hideSoftInputFromWindow(etSearch.windowToken, 0)

                    etSearch.text = null

                    title.visibility = View.VISIBLE
                    etSearch.visibility = View.INVISIBLE
                    flBack.visibility = View.VISIBLE
                    ivSearch.setImageResource(R.drawable.ic_search)
                } else {
                    title.visibility = View.GONE
                    etSearch.visibility = View.VISIBLE
                    ivSearch.setImageResource(R.drawable.ic_gold)
                    flBack.visibility = View.GONE
                }
            }
            flBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }

        observeLiveData()
        mViewModel.getItems()
    }

    private fun searchForItems(itemList: List<Item>) {
        var job: Job? = null

        mBinding.etSearch.addTextChangedListener { editable ->
            job?.cancel()
            job = lifecycleScope.launch {
                delay(300)
                editable?.toString()?.let { searchQuery ->
                    val filteredItems = filterItemByName(itemList, searchQuery)
                    mAdapter.submitList(filteredItems)
                }
            }
        }
    }

    private fun filterItemByName(item: List<Item>, tags: String): List<Item> {
        return item.filter { item ->
            item.name?.contains(tags, ignoreCase = true) == true
        }
    }

    private fun setupBottomSheet(items: List<Item>) {
        itemList = items

        mBinding.flFilter.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(requireContext())
            val bottomSheetView = LayoutInflater.from(requireContext())
                .inflate(R.layout.bottom_sheet_filter_item, mBinding.root, false)
            bottomSheetDialog.setContentView(bottomSheetView)
            bottomSheetDialog.show()

            val mView = BottomSheetFilterItemBinding.bind(bottomSheetView)

            with(mView) {

                val buttons = listOf(
                    Pair(flAll, getString(R.string.items)),
                    Pair(flBoots, getString(R.string.boots)),
                    Pair(flManaRegen, getString(R.string.mana_regen).replace(" ", "")),
                    Pair(flHealthRegen, getString(R.string.health_regen).replace(" ", "")),
                    Pair(flHealth, getString(R.string.health)),
                    Pair(flSpellDamage, getString(R.string.spell_damage).replace(" ", "")),
                    Pair(flMana, getString(R.string.mana)),
                    Pair(flArmor, getString(R.string.armor)),
                    Pair(flSpellBlock, getString(R.string.spell_block).replace(" ", "")),
                    Pair(flLifeSteal, getString(R.string.life_steal).replace(" ", "")),
                    Pair(flSpellVamp, getString(R.string.spell_vamp).replace(" ", "")),
                    Pair(flJungle, getString(R.string.jungle)),
                    Pair(flDamage, getString(R.string.damage)),
                    Pair(flLane, getString(R.string.lane)),
                    Pair(flAttackSpeed, getString(R.string.attack_speed).replace(" ", "")),
                    Pair(flOnHit, getString(R.string.on_hit).replace(" ", "")),
                    Pair(flTrinket, getString(R.string.trinket)),
                    Pair(flActive, getString(R.string.active)),
                    Pair(flConsumable, getString(R.string.consumable)),
                    Pair(flStealth, getString(R.string.stealth)),
                    Pair(flVision, getString(R.string.vision)),
                    Pair(
                        flCoolDownReduction, getString(R.string.cooldownreduction).replace(" ", "")
                    ),
                    Pair(
                        flNonBootsMovement, getString(R.string.nonboots_movement).replace(" ", "")
                    ),
                    Pair(flAbilityHaste, getString(R.string.ability_haste).replace(" ", "")),
                    Pair(flTenacity, getString(R.string.tenacity)),
                    Pair(
                        flArmorPenetration, getString(R.string.armor_penetration).replace(" ", "")
                    ),
                    Pair(
                        flMagicPenetration, getString(R.string.magic_penetration).replace(" ", "")
                    ),
                    Pair(flSlow, getString(R.string.slow)),
                    Pair(flGoldPer, getString(R.string.gold_per).replace(" ", ""))
                )

                buttons.forEach { (button, tag) ->
                    button.setOnClickListener {

                        mBinding.title.text = tag

                        when (tag) {
                            getString(R.string.items) -> mAdapter.submitList(items)
                            else -> mAdapter.submitList(filterItemByTag(items, tag))
                        }
                        bottomSheetDialog.dismiss()
                    }
                }
            }
        }
    }

    private fun filterItemByTag(item: List<Item>, tags: String): List<Item> {
        return item.filter { item ->
            item.tags?.contains(tags) == true
        }
    }

    private fun observeLiveData() {
        mViewModel.items.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { data ->
                        val itemMap = data.data
                        val itemList = itemMap.values.toList()

                        searchForItems(itemList)
                        mAdapter.submitList(itemList)

                        setupBottomSheet(itemList)
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