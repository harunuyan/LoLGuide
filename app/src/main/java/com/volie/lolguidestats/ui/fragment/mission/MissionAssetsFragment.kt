package com.volie.lolguidestats.ui.fragment.mission

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.volie.lolguidestats.databinding.FragmentMissionAssetsBinding
import com.volie.lolguidestats.helper.Constant
import com.volie.lolguidestats.helper.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MissionAssetsFragment : Fragment() {
    private var _mBinding: FragmentMissionAssetsBinding? = null
    private val mBinding get() = _mBinding!!
    private val mViewModel: MissionAssetsViewModel by viewModels()
    private val mAdapter: MissionAssetsRVAdapter by lazy {
        MissionAssetsRVAdapter {
            Glide.with(requireContext())
                .load("${Constant.BASE_URL}img/mission/${it.image?.full}")
                .into(mBinding.ivAssets)

            mBinding.flAssetsDetail.visibility = View.VISIBLE
            mBinding.tvTitle.visibility = View.GONE
            mBinding.view.visibility = View.GONE
            mBinding.flBack.visibility = View.GONE
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentMissionAssetsBinding.inflate(inflater, container, false)
        mBinding.rvMissionAssets.adapter = mAdapter
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(mBinding) {
            flBack.setOnClickListener {
                findNavController().navigateUp()
            }

            ivBack.setOnClickListener {
                flAssetsDetail.visibility = View.GONE
                tvTitle.visibility = View.VISIBLE
                view.visibility = View.VISIBLE
                flBack.visibility = View.VISIBLE
            }
        }

        observeLiveData()
        mViewModel.getAssets()
    }

    private fun observeLiveData() {
        mViewModel.assets.observe(viewLifecycleOwner) { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    resource.data?.let {
                        mAdapter.submitList(it.data.values.toList())
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