package com.sid1817733.sid1817733finalassignment.modulecategory

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sid1817733.sid1817733finalassignment.R
import com.sid1817733.sid1817733finalassignment.moduletypedatabase.ModuleType
import kotlinx.android.synthetic.main.fragment_add_module.*
import kotlinx.android.synthetic.main.fragment_add_module.view.*

class AddModuleTypeFragment : Fragment() {
    private lateinit var mModuleTypeViewModel: ModuleTypeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_module_type, container, false)
        mModuleTypeViewModel = ViewModelProvider(this).get(ModuleTypeViewModel::class.java)
        view.addButton.setOnClickListener {
            insertDataToDatabase()
        }
        return view
    }

    private fun insertDataToDatabase()
    {
        val moduletypeName = addModuleName.text.toString()

        if (inputCheck(moduletypeName))
        {
            val moduletype = ModuleType(0, moduletypeName)

            mModuleTypeViewModel.addModuleType(moduletype)
            Toast.makeText(requireContext(),"Successfully added the module type to the database!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addModuleTypeFragment_to_moduleTypeChooseFragment)
        }
        else{
            Toast.makeText(requireContext(),"Please fill in the text field", Toast.LENGTH_SHORT).show()

        }


    }

    private fun inputCheck(moduleName:String):Boolean{
        return !(TextUtils.isEmpty(moduleName))
    }


}