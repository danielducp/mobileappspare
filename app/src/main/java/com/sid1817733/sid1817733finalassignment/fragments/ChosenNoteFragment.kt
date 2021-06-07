package com.sid1817733.sid1817733finalassignment.fragments

import android.app.AlertDialog
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sid1817733.sid1817733finalassignment.databinding.FragmentChosenNoteBinding

import com.sid1817733.sid1817733finalassignment.note.NoteViewModel
import java.io.ByteArrayInputStream

class ChosenNoteFragment : Fragment() {
    private var _binding: FragmentChosenNoteBinding? = null
    private val binding get() = _binding!!
    private val mNoteViewModel: NoteViewModel by activityViewModels()
    private val args by navArgs<ChosenNoteFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChosenNoteBinding.inflate(inflater, container, false)

        binding.noteNameChosen.setText(args.currentNote.noteName).toString()
        binding.noteInformationChosen.setText(args.currentNote.noteInformation).toString()
        val imageStream = ByteArrayInputStream(args.currentNote.image)
        val theImage = BitmapFactory.decodeStream(imageStream)
        binding.imageofNote.setImageBitmap(theImage)


        binding.deleteNoteButton.setOnClickListener {
            deleteNote()

        }

        return binding.root

    }


    private fun deleteNote() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mNoteViewModel.deleteNote(args.currentNote)
            Toast.makeText(
                requireContext(),
                "Successfully removed: ${args.currentNote.noteName}",
                Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.currentNote.noteName}?")
        builder.setMessage("Are you sure you want to delete the following note: ${args.currentNote.noteName}?")
        builder.create().show()
    }

}