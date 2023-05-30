package com.volie.lolguidestats.ui.fragment.item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.volie.lolguidestats.databinding.FragmentItemBinding
import com.volie.lolguidestats.helper.Status
import com.volie.lolguidestats.ui.adapter.ItemRVAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemFragment : Fragment() {
    private var _mBinding: FragmentItemBinding? = null
    private val mBinding get() = _mBinding!!
    private val mViewModel: ItemViewModel by viewModels()
    private val mAdapter: ItemRVAdapter by lazy {
        ItemRVAdapter(
            onItemClick = {

            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentItemBinding.inflate(inflater, container, false)
        mBinding.rvItems.adapter = mAdapter
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeLiveData()
        mViewModel.getItems()
    }

    private fun observeLiveData() {
        mViewModel.items.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { data ->
                        val itemMap = data.data
                        val itemList = itemMap.values.toList()
                        mAdapter.submitList(itemList)
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