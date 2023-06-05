package com.volie.lolguidestats.ui.fragment.item_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.volie.lolguidestats.databinding.FragmentItemDetailsBinding
import com.volie.lolguidestats.helper.Constant.CHAMPION_IMAGE_URL
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemDetailsFragment : Fragment() {
    private var _mBinding: FragmentItemDetailsBinding? = null
    private val mBinding get() = _mBinding!!
    private val mArgs: ItemDetailsFragmentArgs by navArgs()
    private val intoAdapter: ItemIntoRVAdapter by lazy {
        ItemIntoRVAdapter {

        }
    }
    private val fromAdapter: ItemFromRVAdapter by lazy {
        ItemFromRVAdapter {

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

        intoAdapter.submitList(mArgs.items.into)
        fromAdapter.submitList(mArgs.items.from)

        if (mArgs.items.from.isNullOrEmpty()) {
            mBinding.tvFrom.visibility = View.GONE
            mBinding.rvFrom.visibility = View.GONE
            mBinding.viewFrom.visibility = View.GONE
        }

        if (mArgs.items.into.isNullOrEmpty()) {
            mBinding.tvInto.visibility = View.GONE
            mBinding.rvInto.visibility = View.GONE
            mBinding.viewInto.visibility = View.GONE
        }


        Glide.with(requireContext())
            .load("${CHAMPION_IMAGE_URL}item/${mArgs.items.itemImage?.full}")
            .into(mBinding.ivItemImage)


        if (mArgs.items.gold?.sell != 0) {
            mBinding.tvGoldSell.text = "-${mArgs.items.gold?.sell.toString()}"
        } else {
            mBinding.ivGoldSell.visibility = View.GONE
            mBinding.tvGoldSell.visibility = View.GONE
        }

        if (mArgs.items.gold?.total != 0) {
            mBinding.tvGold.text = mArgs.items.gold?.total.toString()
        } else {
            mBinding.tvGold.visibility = View.GONE
            mBinding.ivGold.visibility = View.GONE
        }

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