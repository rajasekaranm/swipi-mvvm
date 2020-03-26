package com.example.swapi.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.swapi.R
import com.example.swapi.ui.activity.MainActivity
import com.example.swapi.ui.viewmodel.PeopleViewModel
import kotlinx.android.synthetic.main.fragment_people_detail.*

/**
 * A simple [Fragment] subclass.
 */
class PeopleDetailFragment : Fragment(R.layout.fragment_people_detail) {
    private lateinit var peopleViewModel: PeopleViewModel
    var url: String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        url = arguments?.getString("url", "")

        activity?.run {
            peopleViewModel = ViewModelProvider(this, PeopleViewModel).get()
        } ?: throw IllegalStateException("Invalid Activity")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.supportActionBar?.apply {
            this.setTitle("")
            this.setDisplayHomeAsUpEnabled(true)
        }

        peopleViewModel.getPeople(url!!) {
            (activity as? MainActivity)?.run {
                supportActionBar?.setTitle(it.name)
                attributes?.setText(
                    "Gender : ${it.gender}\n"
                            + "mass : ${it.mass}\n"
                            + "height : ${it.height}\n"
                            + "Skin Color : ${it.skinColor}\n"
                            + "Hair Color : ${it.hairColor}\n"
                            + "Eye Color : ${it.eyeColor}\n"
                            + "Birth Year : ${it.birthYear}\n"
                )
            }

        }
    }


}
