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
import com.volie.lolguidestats.R
import com.volie.lolguidestats.data.model.item.Item
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
        ItemRVAdapter(
            onItemClick = {
                val action = ItemFragmentDirections.actionItemFragmentToItemDetailsFragment(it)
                findNavController().navigate(action)
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

    private fun observeLiveData() {
        mViewModel.items.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { data ->
                        val itemMap = data.data
                        val itemList = itemMap.values.toList()

                        searchForItems(itemList)

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