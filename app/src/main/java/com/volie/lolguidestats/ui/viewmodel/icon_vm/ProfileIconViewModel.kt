package com.volie.lolguidestats.ui.viewmodel.icon_vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.volie.lolguidestats.data.model.profile_icon.IconData
import com.volie.lolguidestats.data.remote.repo.Repository
import com.volie.lolguidestats.helper.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileIconViewModel
@Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _icons = MutableLiveData<Resource<IconData>>()
    val icons get() = _icons

    fun getProfileIcons() {
        _icons.postValue(Resource.loading(null))
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getProfileIcons()
            _icons.postValue(result)
        }
    }
}