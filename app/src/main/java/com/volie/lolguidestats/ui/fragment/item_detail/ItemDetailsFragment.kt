package com.volie.lolguidestats.ui.fragment.item_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.volie.lolguidestats.databinding.FragmentItemDetailsBinding
import com.volie.lolguidestats.helper.Constant.BASE_URL
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
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentItemDetailsBinding.inflate(inflater, container, false)
        mBinding.rvInto.adapter = intoAdapter
        mBinding.rvFrom.adapter = fromAdapter
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.flBack.setOnClickListener {
            findNavController().navigateUp()
        }

        showDetails()
    }

    private fun showDetails() {

        with(mBinding) {
            intoAdapter.submitList(mArgs.items.into)
            fromAdapter.submitList(mArgs.items.from)

            if (mArgs.items.from.isNullOrEmpty()) {
                tvFrom.visibility = View.GONE
                rvFrom.visibility = View.GONE
                viewFrom.visibility = View.GONE
            }

            if (mArgs.items.into.isNullOrEmpty()) {
                tvInto.visibility = View.GONE
                rvInto.visibility = View.GONE
                viewInto.visibility = View.GONE
            }


            Glide.with(requireContext()).load("${BASE_URL}img/item/${mArgs.items.itemImage.full}")
                .into(ivItemImage)


            if (mArgs.items.gold?.sell != 0) {
                tvGoldSell.text = "-${mArgs.items.gold?.sell.toString()}"
            } else {
                ivGoldSell.visibility = View.GONE
                tvGoldSell.visibility = View.GONE
            }

            if (mArgs.items.gold?.total != 0) {
                tvGold.text = mArgs.items.gold?.total.toString()
            } else {
                tvGold.visibility = View.GONE
                ivGold.visibility = View.GONE
            }

            tvItemHeader.text = mArgs.items.name
            tvItemName.text = mArgs.items.name
            tvItemPlain.text = mArgs.items.plaintext
            tvItemTags.text = mArgs.items.tags.toString().trim('[', ']')
            tvItemDescription.text =
                mArgs.items.description?.replace("<mainText><stats><attention>", "")
                    ?.replace("<attention>", "")
                    ?.replace("<stats>", "")
                    ?.replace("<status>", "")
                    ?.replace("</status>", "")
                    ?.replace("<mainText>", "")
                    ?.replace("<magicDamage>", "")
                    ?.replace("</magicDamage>", "")
                    ?.replace("</onHit>", "")
                    ?.replace("<onHit>", "")
                    ?.replace("<rarityMythic>", "")
                    ?.replace("</rarityMythic>", "")
                    ?.replace("</rarityLegendary>", "")
                    ?.replace("<rarityLegendary>", "")
                    ?.replace("</attention>", "")
                    ?.replace("</stats>", "")
                    ?.replace("<br>", "\n")
                    ?.replace("<passive>", "")
                    ?.replace("<li>", "")
                    ?.replace("</passive>", "")
                    ?.replace("</li>", "")
                    ?.replace("</mainText>", "")
                    ?.replace("<active>", "")
                    ?.replace("</active>", "")
                    ?.replace("<scalemana>", "")
                    ?.replace("</scalemana>", "")
                    ?.replace("<rules>", "")
                    ?.replace("</rules>", "")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}