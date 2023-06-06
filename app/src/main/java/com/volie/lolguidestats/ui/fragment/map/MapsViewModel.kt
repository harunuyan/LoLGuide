package com.volie.lolguidestats.ui.fragment.map

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.volie.lolguidestats.data.model.map.MapData
import com.volie.lolguidestats.data.remote.Repository
import com.volie.lolguidestats.helper.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapsViewModel
@Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _maps = MutableLiveData<Resource<MapData>>()
    val maps get() = _maps

    fun getMaps() {
        _maps.postValue(Resource.loading(null))
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getMaps()
            _maps.postValue(result)
        }
    }
}