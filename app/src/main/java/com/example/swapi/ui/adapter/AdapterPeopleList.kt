package com.example.swapi.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.swapi.data.model.PeopleRemote
import com.example.swapi.ui.widget.holder.OnPeopleClickListener
import com.example.swapi.ui.widget.holder.ViewHolderPeopleList

class AdapterPeopleList(
    private val listener: OnPeopleClickListener
) : PagedListAdapter<PeopleRemote, ViewHolderPeopleList>(Companion) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPeopleList {
        return ViewHolderPeopleList.create(parent)
    }

    override fun onBindViewHolder(holder: ViewHolderPeopleList, position: Int) {
        println("count    $position")
        getItem(position)?.run { holder.onBind(this, listener) }
    }

    companion object : DiffUtil.ItemCallback<PeopleRemote>() {
        override fun areItemsTheSame(oldItem: PeopleRemote, newItem: PeopleRemote): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: PeopleRemote, newItem: PeopleRemote): Boolean {
            return oldItem == newItem
        }
    }
}