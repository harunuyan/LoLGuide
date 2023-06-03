package com.volie.lolguidestats.ui.fragment.item_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.volie.lolguidestats.databinding.FragmentItemDetailsBinding
import com.volie.lolguidestats.helper.Constant.ITEM_IMAGE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemDetailsFragment : Fragment() {
    private var _mBinding: FragmentItemDetailsBinding? = null
    private val mBinding get() = _mBinding!!
    private val mArgs: ItemDetailsFragmentArgs by navArgs()
    private val intoAdapter: ItemIntoAdapter by lazy {
        ItemIntoAdapter {

        }
    }
    private val fromAdapter: ItemFromAdapter by lazy {
        ItemFromAdapter {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentItemDetailsBinding.inflate(inflater, container, false)
        mBinding.rvInto.adapter = intoAdapter
        mBinding.rvFrom.adapter = fromAdapter
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showDetails()
    }

    private fun showDetails() {

        intoAdapter.submitList(listOf(mArgs.items))
        fromAdapter.submitList(listOf(mArgs.items))

        Glide.with(requireContext())
            .load("$ITEM_IMAGE${mArgs.items.itemImage?.full}")
            .into(mBinding.ivItemImage)

        mBinding.tvGold.text = mArgs.items.gold?.total.toString()
        mBinding.tvGoldSell.text = "-${mArgs.items.gold?.sell.toString()}"

        mBinding.tvItemName.text = mArgs.items.name
        mBinding.tvItemPlain.text = mArgs.items.plaintext
        mBinding.tvItemTags.text = mArgs.items.tags.toString().trim('[', ']')
        mBinding.tvItemDescription.text = mArgs.items.description
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}