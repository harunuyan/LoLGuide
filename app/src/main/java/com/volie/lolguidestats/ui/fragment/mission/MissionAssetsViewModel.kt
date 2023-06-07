package com.volie.lolguidestats.ui.fragment.mission

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.volie.lolguidestats.data.model.mission.MissionAssetsData
import com.volie.lolguidestats.data.remote.Repository
import com.volie.lolguidestats.helper.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MissionAssetsViewModel
@Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _assets = MutableLiveData<Resource<MissionAssetsData>>()
    val assets: LiveData<Resource<MissionAssetsData>> = _assets

    fun getAssets() {
        _assets.postValue(Resource.loading(null))
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getAssets()
            _assets.postValue(result)
        }
    }
}