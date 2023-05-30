package com.volie.lolguidestats.ui.fragment.item

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.volie.lolguidestats.data.model.item.ItemData
import com.volie.lolguidestats.data.remote.Repository
import com.volie.lolguidestats.helper.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemViewModel
@Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _items = MutableLiveData<Resource<ItemData>>()
    val items: LiveData<Resource<ItemData>> = _items

    fun getItems() {
        viewModelScope.launch(Dispatchers.IO) {
            _items.postValue(Resource.loading(null))
            val result = repository.getItems()
            _items.postValue(result)
        }
    }
}