package com.volie.lolguidestats.ui.viewmodel.rank_vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.volie.lolguidestats.data.model.rank.SeasonData
import com.volie.lolguidestats.data.remote.repo.Repository
import com.volie.lolguidestats.helper.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RankViewModel
@Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _ranks = MutableLiveData<Resource<SeasonData>>()
    val ranks: LiveData<Resource<SeasonData>> = _ranks

    fun getRanks() {
        _ranks.postValue(Resource.loading(null))
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getRank()
            _ranks.postValue(result)
        }
    }
}