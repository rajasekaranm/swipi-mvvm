package com.example.swapi.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.swapi.R
import com.example.swapi.ui.viewmodel.PeopleViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var peopleViewModel: PeopleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        peopleViewModel = ViewModelProvider(this, PeopleViewModel).get()
        setContentView(R.layout.activity_main)
        peopleViewModel.getPeople(1,{
            text.text="${Gson().toJson(it)}"
        })
    }
}
