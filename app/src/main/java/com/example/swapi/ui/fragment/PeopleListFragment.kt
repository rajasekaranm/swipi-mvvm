package com.example.swapi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController

import com.example.swapi.R
import com.example.swapi.data.model.PeopleRemote
import com.example.swapi.ui.activity.MainActivity
import com.example.swapi.ui.adapter.AdapterPeopleList
import com.example.swapi.ui.viewmodel.PeopleViewModel
import com.example.swapi.ui.widget.holder.OnPeopleClickListener
import kotlinx.android.synthetic.main.fragment_people_list.*


class PeopleListFragment : Fragment(R.layout.fragment_people_list) {
    private lateinit var peopleViewModel: PeopleViewModel
    private lateinit var adapterPeopleList: AdapterPeopleList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.run {
            peopleViewModel = ViewModelProvider(this, PeopleViewModel).get()
        } ?: throw IllegalStateException("Invalid Activity")
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? MainActivity)?.supportActionBar?.apply {
            this.setTitle("People List")
            this.setDisplayHomeAsUpEnabled(false)
        }

        adapterPeopleList = AdapterPeopleList(object :
            OnPeopleClickListener {
            override fun invoke(view: View, p: PeopleRemote) {
                findNavController().navigate(R.id.action_people_list_fragment_to_people_details_fragment,
                    Bundle().apply {
                        putString("url", p.url)
                    }
                )
            }
        })

        recycler_peoples_list.apply { adapter = adapterPeopleList }

        peopleViewModel.peoplePagedList.observe(this) {
            adapterPeopleList.submitList(it)
        }
    }

}
