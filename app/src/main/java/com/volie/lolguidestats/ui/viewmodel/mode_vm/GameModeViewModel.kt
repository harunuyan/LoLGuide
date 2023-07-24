package com.volie.lolguidestats.ui.viewmodel.mode_vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.volie.lolguidestats.data.model.mode.ModeData
import com.volie.lolguidestats.data.remote.repo.Repository
import com.volie.lolguidestats.helper.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameModeViewModel
@Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _modes = MutableLiveData<Resource<ModeData>>()
    val modes: LiveData<Resource<ModeData>> = _modes

    fun getGameModes() {
        _modes.postValue(Resource.loading(null))
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getGameModes()
            _modes.postValue(result)
        }
    }
}