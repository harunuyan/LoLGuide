package com.volie.lolguidestats.ui.fragment.summoner_spell

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.volie.lolguidestats.data.model.summoner_spell.SummonerSpellData
import com.volie.lolguidestats.data.remote.Repository
import com.volie.lolguidestats.helper.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SummonerSpellViewModel
@Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _summonerSpells = MutableLiveData<Resource<SummonerSpellData>>()
    val summonerSpell: LiveData<Resource<SummonerSpellData>> = _summonerSpells

    fun getSummonerSpell() {
        _summonerSpells.postValue(Resource.loading(null))
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getSummonerSpell()
            _summonerSpells.postValue(result)
        }
    }
}