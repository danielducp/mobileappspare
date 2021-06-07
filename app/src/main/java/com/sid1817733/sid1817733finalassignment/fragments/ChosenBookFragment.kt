package com.sid1817733.sid1817733finalassignment.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.sid1817733.sid1817733finalassignment.book.BookViewModel
import com.sid1817733.sid1817733finalassignment.bookdatabase.Book
import com.sid1817733.sid1817733finalassignment.databinding.FragmentChosenBookBinding
import com.sid1817733.sid1817733finalassignment.module.ModuleViewModel
import kotlinx.android.synthetic.main.fragment_chosen_book.*

class ChosenBookFragment : Fragment() {
    private var _binding: FragmentChosenBookBinding? = null
    private val binding get() = _binding!!
    private val mBookViewModel: BookViewModel by activityViewModels()
    private val mModuleViewModel: ModuleViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChosenBookBinding.inflate(inflater, container, false)

        mModuleViewModel.module.observe(viewLifecycleOwner) { module ->
            binding.moduleName.setText(module).toString()

        }


        mBookViewModel.book.observe(viewLifecycleOwner) { book ->
            binding.bookName.setText(book).toString()

        }

        binding.addButton.setOnClickListener {
            insertDataToDatabase()

        }


    return binding.root
    }
    private fun insertDataToDatabase()
    {
        val moduleName = moduleName.text.toString()
        val bookName = bookName.text.toString()


        if (inputCheck(bookName, moduleName))
        {
            val book = Book(0, bookName, moduleName)

            mBookViewModel.addBook(book)
            Toast.makeText(requireContext(),"Successfully added the book to the database!", Toast.LENGTH_SHORT).show()
            mBookViewModel.saveBook( binding.moduleName.setText(moduleName).toString())
            findNavController().popBackStack()


        }
        else{
            Toast.makeText(requireContext(),"Please fill in every text field", Toast.LENGTH_SHORT).show()

        }


    }

    private fun inputCheck(bookName:String, moduleName:String ):Boolean{
        return !(TextUtils.isEmpty(bookName)||(TextUtils.isEmpty(moduleName)))
    }

    }
