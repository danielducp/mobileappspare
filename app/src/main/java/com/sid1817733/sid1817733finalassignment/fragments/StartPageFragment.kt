package com.sid1817733.sid1817733finalassignment.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sid1817733.sid1817733finalassignment.R
import kotlinx.android.synthetic.main.fragment_start_page.view.*


class StartPageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_start_page, container, false)

        view.startButton.setOnClickListener {
            findNavController().navigate(R.id.action_startPageFragment_to_moduleTypeChooseFragment)}


        return view
    }



}