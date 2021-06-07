package com.sid1817733.sid1817733finalassignment.module

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
import com.sid1817733.sid1817733finalassignment.databinding.FragmentAddModuleBinding
import com.sid1817733.sid1817733finalassignment.modulecategory.ModuleTypeViewModel
import com.sid1817733.sid1817733finalassignment.moduledatabase.Module
import kotlinx.android.synthetic.main.fragment_add_module.*
import kotlinx.android.synthetic.main.fragment_add_module.addModuleName


class AddModuleFragment : Fragment() {

    private var _binding: FragmentAddModuleBinding? = null
    private lateinit var mModuleViewModel: ModuleViewModel
    private val mModuleTypeViewModel: ModuleTypeViewModel by activityViewModels()

    private val args by navArgs<AddModuleFragmentArgs>()

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentAddModuleBinding.inflate(inflater, container, false)


        mModuleTypeViewModel.moduletype.observe(viewLifecycleOwner) { moduletype ->
            binding.addtextModuleType.setText(moduletype).toString()

        }


        mModuleViewModel = ViewModelProvider(this).get(ModuleViewModel::class.java)
        binding.addButton.setOnClickListener {
            insertDataToDatabase()
        }


        return binding.root
    }
    private fun insertDataToDatabase()
    {
        val moduleName = addModuleName.text.toString()
        val moduleType = addtextModuleType.text.toString()


        if (inputCheck(moduleName, moduleType))
        {
            val module = Module(0, moduleName, moduleType)

                 mModuleViewModel.addModule(module)
            Toast.makeText(requireContext(),"Successfully added the module to the database!", Toast.LENGTH_SHORT).show()
            mModuleTypeViewModel.saveModuleType( binding.addtextModuleType.setText(moduleType).toString())
            findNavController().popBackStack()


        }
        else{
            Toast.makeText(requireContext(),"Please fill in every text field", Toast.LENGTH_SHORT).show()

        }


    }

    private fun inputCheck(moduleName:String, moduleType:String ):Boolean{
        return !(TextUtils.isEmpty(moduleName)||(TextUtils.isEmpty(moduleType)))
    }

}