package com.volie.lolguidestats.ui.fragment.home

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
class HomeViewModel
@Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _champ = MutableLiveData<Resource<Data>>()
    val champ: LiveData<Resource<Data>> = _champ

    fun getChamp() {
        _champ.postValue(Resource.loading(null))
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getChampions(".json")
            _champ.postValue(result)
        }
    }
}