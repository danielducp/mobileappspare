package com.sid1817733.sid1817733finalassignment.note

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap

import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.drawToBitmap
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sid1817733.sid1817733finalassignment.databinding.FragmentAddNoteBinding
import com.sid1817733.sid1817733finalassignment.module.ModuleViewModel
import com.sid1817733.sid1817733finalassignment.notedatabase.Note
import kotlinx.android.synthetic.main.fragment_add_assignment.*
import kotlinx.android.synthetic.main.fragment_add_assignment.moduleNameBox
import kotlinx.android.synthetic.main.fragment_add_module.addModuleName
import kotlinx.android.synthetic.main.fragment_add_note.*
import java.io.ByteArrayOutputStream



class AddNoteFragment : Fragment() {

    private var _binding: FragmentAddNoteBinding? = null
    private lateinit var mNoteViewModel: NoteViewModel
    private val mModuleViewModel: ModuleViewModel by activityViewModels()
    private val cameraRequest = 1888
    lateinit var imageView: ImageView
    private val args by navArgs<AddNoteFragmentArgs>()

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentAddNoteBinding.inflate(inflater, container, false)


        mModuleViewModel.module.observe(viewLifecycleOwner) { module ->
            binding.moduleNameChosen.setText(module).toString()
            binding.moduleNameBox.setText(module).toString()

        }


        mNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        binding.addButton.setOnClickListener {
            insertDataToDatabase()
        }

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_DENIED)
            requestPermissions(arrayOf(Manifest.permission.CAMERA), cameraRequest)
        imageView = binding.imageView
        val photoButton: Button = binding.cameraButton
        photoButton.setOnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, cameraRequest)
        }


        return binding.root
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == cameraRequest) {
            val photo: Bitmap = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(photo)


        }
    }


    private fun insertDataToDatabase()
    {
        val noteName = addNoteName.text.toString()
        val noteInformation = noteInformation.text.toString()
        val moduleNameTest = moduleNameBox.text.toString()
        var imagePath:String?=null;



     val image: Bitmap = imageView.drawToBitmap()
        val stream = ByteArrayOutputStream()

        image.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        val imageInByte: ByteArray = stream.toByteArray()

        if (inputCheck(noteName, noteInformation,  imageInByte, moduleNameTest))
        {
            val note = Note(noteName, noteInformation, moduleNameTest, imageInByte)

            mNoteViewModel.addNote(note)
            Toast.makeText(requireContext(),"Successfully added the note to the database!", Toast.LENGTH_SHORT).show()
            mModuleViewModel.saveModule(moduleNameBox.text.toString())
            findNavController().popBackStack()
        }
        else{
            Toast.makeText(requireContext(),"Please fill in every text field", Toast.LENGTH_SHORT).show()

        }


    }


    private fun inputCheck(noteName:String, noteInformation:String, imageInByte:ByteArray, moduleName:String ):Boolean{
        return !(TextUtils.isEmpty(noteName)||(TextUtils.isEmpty(noteInformation)||(TextUtils.isEmpty(moduleName))))
    }

}