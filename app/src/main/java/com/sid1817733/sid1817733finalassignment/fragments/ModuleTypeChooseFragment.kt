package com.sid1817733.sid1817733finalassignment.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sid1817733.sid1817733finalassignment.R
import com.sid1817733.sid1817733finalassignment.databinding.FragmentModuleTypeChooseBinding
import com.sid1817733.sid1817733finalassignment.modulecategory.ModuleTypeListAdapter
import com.sid1817733.sid1817733finalassignment.modulecategory.ModuleTypeViewModel


class ModuleTypeChooseFragment : Fragment() {
    private var _binding: FragmentModuleTypeChooseBinding? = null
    private val binding get() = _binding!!
    private lateinit var mModuleTypeViewModel: ModuleTypeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentModuleTypeChooseBinding.inflate(inflater, container, false)

        val adapter = ModuleTypeListAdapter()
        val recyclerView = binding.moduletyperecyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mModuleTypeViewModel = ViewModelProvider(this).get(ModuleTypeViewModel::class.java)
        mModuleTypeViewModel.readAllData.observe(viewLifecycleOwner, Observer {moduletype ->

            adapter.setData(moduletype)
        })

        binding.floatingActionButtonForModuleType.setOnClickListener {

            findNavController().navigate(R.id.action_moduleTypeChooseFragment_to_addModuleTypeFragment)}
        return binding.root
    }


}
