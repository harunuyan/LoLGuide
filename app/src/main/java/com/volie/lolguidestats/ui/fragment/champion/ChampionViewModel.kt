package com.volie.lolguidestats.ui.fragment.champion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.volie.lolguidestats.data.model.champion.Data
import com.volie.lolguidestats.data.remote.Repository
import com.volie.lolguidestats.helper.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChampionViewModel
@Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _champs = MutableLiveData<Resource<Data>>()
    val champs: LiveData<Resource<Data>> = _champs

    fun getChampions() {
        _champs.postValue(Resource.loading(null))
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getChampions()
            _champs.postValue(result)
        }
    }
}