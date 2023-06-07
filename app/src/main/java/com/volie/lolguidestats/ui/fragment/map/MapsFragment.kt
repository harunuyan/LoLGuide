package com.volie.lolguidestats.ui.fragment.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.volie.lolguidestats.databinding.FragmentMapsBinding
import com.volie.lolguidestats.helper.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapsFragment : Fragment() {
    private var _mBinding: FragmentMapsBinding? = null
    private val mBinding get() = _mBinding!!
    private val mAdapter = MapsRVAdapter()
    private val mViewModel: MapsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentMapsBinding.inflate(inflater, container, false)
        mBinding.rvMaps.adapter = mAdapter
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.flBack.setOnClickListener {
            findNavController().navigateUp()
        }

        mViewModel.getMaps()
        observeLiveData()
    }

    private fun observeLiveData() {
        mViewModel.maps.observe(viewLifecycleOwner) { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    resource.data?.let {
                        val mapsMap = it.data
                        val mapList = mapsMap.values.toList()

                        mAdapter.submitList(mapList)
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