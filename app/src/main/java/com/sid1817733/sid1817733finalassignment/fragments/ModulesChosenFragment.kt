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
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.sid1817733.sid1817733finalassignment.R
import com.sid1817733.sid1817733finalassignment.assignments.AssignmentViewModel
import com.sid1817733.sid1817733finalassignment.databinding.FragmentModulesChosenBinding
import com.sid1817733.sid1817733finalassignment.module.ModuleListAdapter
import com.sid1817733.sid1817733finalassignment.module.ModuleViewModel
import com.sid1817733.sid1817733finalassignment.modulecategory.ModuleTypeViewModel
import com.sid1817733.sid1817733finalassignment.moduledatabase.Module
import com.sid1817733.sid1817733finalassignment.moduledatabase.ModuleDatabase
import com.sid1817733.sid1817733finalassignment.moduledatabase.ModuleRepository
import com.sid1817733.sid1817733finalassignment.note.NoteViewModel

class ModulesChosenFragment : Fragment() {
    private var _binding: FragmentModulesChosenBinding? = null
    private val binding get() = _binding!!
    private val mModuleTypeViewModel: ModuleTypeViewModel by activityViewModels()
    private val mModuleViewModel1: ModuleViewModel by activityViewModels()
    private val mAssignmentViewModel: AssignmentViewModel by activityViewModels()
    private val mNoteViewModel : NoteViewModel by activityViewModels()
    private lateinit var mModuleViewModel: ModuleViewModel

    private val args by navArgs<ModulesChosenFragmentArgs>()


        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            _binding = FragmentModulesChosenBinding.inflate(inflater, container, false)

            val adapter = ModuleListAdapter()
            val recyclerView = binding.modulerecyclerview
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())

            mModuleTypeViewModel.moduletype.observe(viewLifecycleOwner) { moduletype ->
                binding.viewModuleTypeChosenName.setText(moduletype).toString()

            }

            mModuleTypeViewModel.moduletype.observe(viewLifecycleOwner) { moduletype ->

                binding.viewModuleTypeChosenName.setText(moduletype).toString()

                binding.viewModuleTypeChosenName.setText(args.currentModuleType.moduleTypeName).toString()
                val repository: ModuleRepository
                val moduleDao = ModuleDatabase.getDatabase(requireContext()).moduleDao()
                repository = ModuleRepository(moduleDao, args.currentModuleType.moduleTypeName)
                val readAllDataSpare: LiveData<List<Module>>

                readAllDataSpare = repository.getAllModules(args.currentModuleType.moduleTypeName)
                Log.d("My tag",args.currentModuleType.moduleTypeName)
                readAllDataSpare.observe(viewLifecycleOwner, Observer {module ->

                    adapter.setData(module, args.currentModuleType.moduleTypeName)
                })
            }

            mModuleViewModel = ViewModelProvider(this).get(ModuleViewModel::class.java)


            binding.floatingActionButtonforModules.setOnClickListener {
                mModuleTypeViewModel.saveModuleType(args.currentModuleType.moduleTypeName)

                findNavController().navigate(R.id.action_modulesChosenFragment_to_addModuleFragment)}


            binding.deletemoduletypebutton.setOnClickListener {
                deleteModuleType()

            }


            return binding.root
        }

    private fun deleteModuleType() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
           val name = mModuleTypeViewModel.deleteModuleType(args.currentModuleType)

            mModuleViewModel1.deleteModuleFromType(args.currentModuleType.moduleTypeName)

            mAssignmentViewModel.deleteAssignmentFromModule(mModuleViewModel.readAllDataName(args.currentModuleType.moduleTypeName).toString())
            mNoteViewModel.deleteNoteFromModule(args.currentModuleType.moduleTypeName)

            Toast.makeText(
                requireContext(),
                "Successfully removed: ${args.currentModuleType.moduleTypeName}",
                Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.currentModuleType.moduleTypeName}?")
        builder.setMessage("Are you sure you want to delete the following Module Type: ${args.currentModuleType.moduleTypeName}?")
        builder.create().show()
    }
    }
