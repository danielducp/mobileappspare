package com.sid1817733.sid1817733finalassignment.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sid1817733.sid1817733finalassignment.R
import com.sid1817733.sid1817733finalassignment.assignments.AssignmentViewModel
import com.sid1817733.sid1817733finalassignment.databinding.FragmentViewChosenModuleBinding
import com.sid1817733.sid1817733finalassignment.module.ModuleViewModel
import com.sid1817733.sid1817733finalassignment.note.NoteViewModel

class ViewChosenModuleFragment : Fragment() {
    private var _binding: FragmentViewChosenModuleBinding? = null
    private val binding get() = _binding!!
    private val mModuleViewModel: ModuleViewModel by activityViewModels()
    private val mAssignmentViewModel: AssignmentViewModel by activityViewModels()
    private val mNoteViewModel: NoteViewModel by activityViewModels()

    private val args by navArgs<ViewChosenModuleFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentViewChosenModuleBinding.inflate(inflater, container, false)

        binding.moduleNameChosen.setText(args.currentModule.moduleName).toString()

        mModuleViewModel.module.observe(viewLifecycleOwner) { module ->

        }

        binding.assignmentsButton.setOnClickListener {

            mModuleViewModel.saveModule(binding.moduleNameChosen.text.toString())
            Log.d("Module name tag", binding.moduleNameChosen.text.toString())
            Log.d("Click tag", "Clicked")

            findNavController().navigate(R.id.action_viewChosenModuleFragment_to_assignmentFromModuleFragment)
        }

        binding.booksButton.setOnClickListener {

            mModuleViewModel.saveModule(binding.moduleNameChosen.text.toString())
            findNavController().navigate(R.id.action_viewChosenModuleFragment_to_apiFragment)
        }
        binding.notesButton.setOnClickListener {

            mModuleViewModel.saveModule(binding.moduleNameChosen.text.toString())
            findNavController().navigate(R.id.action_viewChosenModuleFragment_to_notesFromModuleFragment)
        }

        binding.currentBooksButtton.setOnClickListener {

            mModuleViewModel.saveModule(binding.moduleNameChosen.text.toString())

                findNavController().navigate(R.id.action_viewChosenModuleFragment_to_viewChosenBooksFragment)
            }

        binding.deletebutton.setOnClickListener {
        deleteModule()

        }

        return binding.root

    }

    private fun deleteModule() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mModuleViewModel.deleteModule(args.currentModule)
            mNoteViewModel.deleteNoteFromModule(args.currentModule.moduleName)
            mAssignmentViewModel.deleteAssignmentFromModule(args.currentModule.moduleName)
            Toast.makeText(
                    requireContext(),
                    "Successfully removed: ${args.currentModule.moduleName}",
                    Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.currentModule.moduleName}?")
        builder.setMessage("Are you sure you want to delete the following module: ${args.currentModule.moduleName}?")
        builder.create().show()
    }

}