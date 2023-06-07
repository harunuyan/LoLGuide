package com.volie.lolguidestats.ui.fragment.mode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.volie.lolguidestats.databinding.FragmentGameModeBinding
import com.volie.lolguidestats.helper.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameModeFragment : Fragment() {
    private var _mBinding: FragmentGameModeBinding? = null
    private val mBinding get() = _mBinding!!
    private val mViewModel: GameModeViewModel by viewModels()
    private val mAdapter: GameModeRVAdapter by lazy {
        GameModeRVAdapter {
            val action = GameModeFragmentDirections.actionGameModeFragmentToModeDetailsFragment(it)
            findNavController().navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentGameModeBinding.inflate(inflater, container, false)
        mBinding.rvGameModes.adapter = mAdapter
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.flBack.setOnClickListener {
            findNavController().navigateUp()
        }

        observeLiveData()
        mViewModel.getGameModes()
    }

    private fun observeLiveData() {
        mViewModel.modes.observe(viewLifecycleOwner) { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    resource.data?.let {
                        mAdapter.submitList(it.modes)
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