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
import com.sid1817733.sid1817733finalassignment.databinding.FragmentNotesFromModuleBinding
import com.sid1817733.sid1817733finalassignment.module.ModuleViewModel
import com.sid1817733.sid1817733finalassignment.note.NoteListAdapter
import com.sid1817733.sid1817733finalassignment.note.NoteViewModel
import com.sid1817733.sid1817733finalassignment.notedatabase.Note
import com.sid1817733.sid1817733finalassignment.notedatabase.NoteDatabase
import com.sid1817733.sid1817733finalassignment.notedatabase.NoteRepository

class   NotesFromModuleFragment : Fragment() {
    private var _binding: FragmentNotesFromModuleBinding? = null
    private val binding get() = _binding!!
    private val mModuleViewModel: ModuleViewModel by activityViewModels()
    private lateinit var mNoteViewViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {




        _binding = FragmentNotesFromModuleBinding.inflate(inflater, container, false)

        val adapter = NoteListAdapter()
        val recyclerView = binding.noterecyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mModuleViewModel.module.observe(viewLifecycleOwner) { module ->


            binding.moduleNameOnNote.setText(module).toString()
            val repository: NoteRepository
            val noteDao = NoteDatabase.getDatabase(requireContext()).noteDao()
            repository = NoteRepository(noteDao, module)
            val readAllDataSpare: LiveData<List<Note>>

            readAllDataSpare = repository.getAllNotes(module)
            Log.d("My tag",module)
            readAllDataSpare.observe(viewLifecycleOwner, Observer {note ->
                binding.moduleNameOnNote.setText(module).toString()

                adapter.setData(note, module)

            })
        }

        mNoteViewViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)


        binding.floatingActionButtonForNotes.setOnClickListener {

            findNavController().navigate(R.id.action_notesFromModuleFragment_to_addNoteFragment)}
        return binding.root
    }


}
