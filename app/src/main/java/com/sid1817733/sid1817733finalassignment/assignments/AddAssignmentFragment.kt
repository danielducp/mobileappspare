package com.sid1817733.sid1817733finalassignment.assignments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sid1817733.sid1817733finalassignment.assignmentdatabase.Assignment
import com.sid1817733.sid1817733finalassignment.databinding.FragmentAddAssignmentBinding
import com.sid1817733.sid1817733finalassignment.module.ModuleViewModel
import kotlinx.android.synthetic.main.fragment_add_assignment.*
import kotlinx.android.synthetic.main.fragment_add_module.addModuleName


class AddAssignmentFragment : Fragment() {

    private var _binding: FragmentAddAssignmentBinding? = null
    private lateinit var mAssignmentViewModel: AssignmentViewModel
    private val mModuleViewModel: ModuleViewModel by activityViewModels()

    private val args by navArgs<AddAssignmentFragmentArgs>()

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentAddAssignmentBinding.inflate(inflater, container, false)


        mModuleViewModel.module.observe(viewLifecycleOwner) { module ->
            binding.moduleNameChosen.setText(module).toString()
            binding.moduleNameBox.setText(module).toString()

        }


        mAssignmentViewModel = ViewModelProvider(this).get(AssignmentViewModel::class.java)
        binding.addButton.setOnClickListener {
            insertDataToDatabase()
        }


        return binding.root
    }
    private fun insertDataToDatabase()
    {
        val assignmentName = addModuleName.text.toString()
        val assignmentInformation = assignmentInformation.text.toString()
        val moduleNameTest = moduleNameBox.text.toString()







        if (inputCheck(assignmentName, assignmentInformation, moduleNameTest))
        {
            val assignment = Assignment(0, assignmentName, assignmentInformation, moduleNameTest)

            mAssignmentViewModel.addAssignment(assignment)
            Toast.makeText(requireContext(),"Successfully added the assignment to the database!", Toast.LENGTH_SHORT).show()
            mModuleViewModel.saveModule(moduleNameBox.text.toString())
            findNavController().popBackStack()
        }
        else{
            Toast.makeText(requireContext(),"Please fill in every text field", Toast.LENGTH_SHORT).show()

        }


    }

    private fun inputCheck(assignmentName:String, assignmentInformation:String, moduleName:String ):Boolean{
        return !(TextUtils.isEmpty(assignmentName)||(TextUtils.isEmpty(assignmentInformation)||(TextUtils.isEmpty(moduleName))))
    }

}