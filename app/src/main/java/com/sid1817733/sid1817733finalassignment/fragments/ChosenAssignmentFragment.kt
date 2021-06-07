package com.sid1817733.sid1817733finalassignment.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sid1817733.sid1817733finalassignment.assignments.AssignmentViewModel
import com.sid1817733.sid1817733finalassignment.databinding.FragmentChosenAssignmentBinding


class ChosenAssignmentFragment : Fragment() {
    private var _binding: FragmentChosenAssignmentBinding? = null
    private val binding get() = _binding!!
    private val mAssignmentViewModel: AssignmentViewModel by activityViewModels()
    private val args by navArgs<ChosenAssignmentFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChosenAssignmentBinding.inflate(inflater, container, false)

        binding.assignmentInformationChosen.setText(args.currentAssignment.assignmentInformation).toString()
        binding.assignmentNameChosen.setText(args.currentAssignment.assignmentName).toString()


        binding.deleteassignmentbutton.setOnClickListener {
            deleteAssignment()

        }

        return binding.root

    }


    private fun deleteAssignment() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mAssignmentViewModel.deleteAssignment(args.currentAssignment)
            Toast.makeText(
                requireContext(),
                "Successfully removed: ${args.currentAssignment.assignmentName}",
                Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.currentAssignment.assignmentName}?")
        builder.setMessage("Are you sure you want to delete the following assignment: ${args.currentAssignment.assignmentName}?")
        builder.create().show()
    }

}