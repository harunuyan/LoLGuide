package com.volie.lolguidestats.ui.fragment.champ_detail

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
class ChampionsDetailsViewModel
@Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _champions = MutableLiveData<Resource<Data>>()
    val champions: LiveData<Resource<Data>> = _champions

    fun getChampionDetails(champ: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getChampions("/${champ}.json")
            _champions.postValue(result)
        }
    }
}