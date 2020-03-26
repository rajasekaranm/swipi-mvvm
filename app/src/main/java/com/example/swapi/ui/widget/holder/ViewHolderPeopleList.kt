package com.example.swapi.ui.widget.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.swapi.R
import com.example.swapi.data.model.PeopleRemote
import kotlinx.android.synthetic.main.item_people_list.view.*

typealias OnPeopleClickListener = (view: View, p: PeopleRemote) -> Unit

class ViewHolderPeopleList private constructor(view: View) : RecyclerView.ViewHolder(view) {

    fun onBind(p: PeopleRemote, listener: OnPeopleClickListener) {
        itemView.name.setText(p.name)
        itemView.attributes.setText("Gender : ${p.gender!!.capitalize()}")
        itemView.setOnClickListener{listener.invoke(itemView,p)}
    }

    companion object {
        /**
         * Create a new Instance of [CharacterViewHolder]
         */
        fun create(parent: ViewGroup): ViewHolderPeopleList {
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.item_people_list,
                parent,
                false
            )
            return ViewHolderPeopleList(view)
        }
    }
}