package com.sid1817733.sid1817733finalassignment.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sid1817733.sid1817733finalassignment.R
import com.sid1817733.sid1817733finalassignment.assignmentdatabase.Assignment
import com.sid1817733.sid1817733finalassignment.assignmentdatabase.AssignmentDatabase
import com.sid1817733.sid1817733finalassignment.assignmentdatabase.AssignmentRepository
import com.sid1817733.sid1817733finalassignment.assignments.AssignmentListAdapter
import com.sid1817733.sid1817733finalassignment.assignments.AssignmentViewModel
import com.sid1817733.sid1817733finalassignment.databinding.FragmentAssignmentsFromModuleBinding
import com.sid1817733.sid1817733finalassignment.module.ModuleViewModel

class AssignmentFromModuleFragment : Fragment() {
    private var _binding: FragmentAssignmentsFromModuleBinding? = null
    private val binding get() = _binding!!
    private val mModuleViewModel: ModuleViewModel by activityViewModels()
    private lateinit var mAssignmentViewModel: AssignmentViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {




        _binding = FragmentAssignmentsFromModuleBinding.inflate(inflater, container, false)

        val adapter = AssignmentListAdapter()
        val recyclerView = binding.assignmentrecyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mModuleViewModel.module.observe(viewLifecycleOwner) { module ->


            binding.moduleNameOnAssignment.setText(module).toString()
            val repository: AssignmentRepository
            val assignmentDao = AssignmentDatabase.getDatabase(requireContext()).assignmentDao()
            repository = AssignmentRepository(assignmentDao, module)
            val readAllDataSpare: LiveData<List<Assignment>>

            readAllDataSpare = repository.getAllAssignments(module)
            Log.d("My tag",module)
            readAllDataSpare.observe(viewLifecycleOwner, Observer {assignment ->

                adapter.setData(assignment, module)
            })
        }

        mAssignmentViewModel = ViewModelProvider(this).get(AssignmentViewModel::class.java)


        binding.floatingActionButtonForAssignments.setOnClickListener {

            findNavController().navigate(R.id.action_assignmentFromModuleFragment_to_addAssignmentFragment)}
        return binding.root
    }


}
