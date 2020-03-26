package com.example.swapi.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.swapi.data.model.PeopleRemote
import com.example.swapi.data.repository.PeopleRepository
import com.example.swapi.ui.utils.OnSuccess
import kotlinx.coroutines.launch

open class PeopleViewModel(
    private val repository: PeopleRepository
) : ViewModel() {


    val peoplePagedList = repository.getPeoplePagedList(viewModelScope)

    fun getPeople(url: String, onSuccess: OnSuccess<PeopleRemote>) {
        viewModelScope.launch {
            repository.getPeople(url)?.let {
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
