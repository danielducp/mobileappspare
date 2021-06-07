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
import androidx.recyclerview.widget.LinearLayoutManager
import com.sid1817733.sid1817733finalassignment.book.BookListAdapter
import com.sid1817733.sid1817733finalassignment.book.BookViewModel
import com.sid1817733.sid1817733finalassignment.bookdatabase.Book
import com.sid1817733.sid1817733finalassignment.bookdatabase.BookDatabase
import com.sid1817733.sid1817733finalassignment.bookdatabase.BookRepository
import com.sid1817733.sid1817733finalassignment.databinding.FragmentViewChosenBooksBinding
import com.sid1817733.sid1817733finalassignment.module.ModuleViewModel
import com.sid1817733.sid1817733finalassignment.note.NoteViewModel


class ViewChosenBooksFragment : Fragment() {
    private var _binding: FragmentViewChosenBooksBinding? = null
    private val binding get() = _binding!!
    private val mModuleViewModel: ModuleViewModel by activityViewModels()
    private lateinit var mBookViewModel: BookViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentViewChosenBooksBinding.inflate(inflater, container, false)

        val adapter = BookListAdapter()
        val recyclerView = binding.modulerecyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mModuleViewModel.module.observe(viewLifecycleOwner) { module ->


            binding.viewModuleTypeChosenName.setText(module).toString()
            val repository: BookRepository
            val noteDao = BookDatabase.getDatabase(requireContext()).bookDao()
            repository = BookRepository(noteDao, module)
            val readAllDataSpare: LiveData<List<Book>>

            readAllDataSpare = repository.getAllBooks(module)
            Log.d("My tag", module)
            readAllDataSpare.observe(viewLifecycleOwner, Observer { book ->

                adapter.setData(book, module)

            })
        }

        mBookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)
return binding.root
              }
}