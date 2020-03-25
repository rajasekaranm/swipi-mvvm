package com.example.swapi.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.swapi.data.model.PeopleRemote
import com.example.swapi.data.respository.PeopleRepository
import com.example.swapi.ui.utils.OnSuccess
import kotlinx.coroutines.launch

open class PeopleViewModel(
    private val repository: PeopleRepository
) : ViewModel() {

    fun getPeople(id: Int, onSuccess: OnSuccess<PeopleRemote>) {
        viewModelScope.launch {
            repository.getPeople(id)?.let {
                onSuccess.invoke(it)
            }
        }
    }
    companion object Factory : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return PeopleViewModel(PeopleRepository.instance) as T
        }
    }

}
