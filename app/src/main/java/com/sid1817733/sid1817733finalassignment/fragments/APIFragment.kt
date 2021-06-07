package com.sid1817733.sid1817733finalassignment.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.sid1817733.sid1817733finalassignment.R
import com.sid1817733.sid1817733finalassignment.book.BookViewModel
import com.sid1817733.sid1817733finalassignment.databinding.FragmentApiBinding
import com.sid1817733.sid1817733finalassignment.module.ModuleViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


class APIFragment : Fragment() {

    private var _binding: FragmentApiBinding? = null
    private val binding get() = _binding!!
    private val mModuleViewModel: ModuleViewModel by activityViewModels()

    private val mBookViewModel: BookViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentApiBinding.inflate(inflater, container, false)

        mModuleViewModel.module.observe(viewLifecycleOwner) { module ->
            binding.moduleTitle.setText(module).toString()

        }


        getCurrentData()
        val refreshButton: Button = binding.refreshButton
val nextPageButton: Button = binding.nextPageButton

        nextPageButton.setOnClickListener {
            mBookViewModel.saveBook(binding.bookName.text.toString())

            Log.d("tag", binding.bookName.text.toString())
           findNavController().navigate(R.id.action_apiFragment_to_chosenBookFragment2)


        }
        refreshButton.setOnClickListener()
        {
            getCurrentData()
        }


        return binding.root


    }






    private fun getCurrentData() {
        val bookNameText: TextView = binding.bookName
        bookNameText.visibility = View.INVISIBLE
        val bookAuthorText: TextView = binding.bookAuthor
        bookAuthorText.visibility = View.INVISIBLE

        val progressBar: ProgressBar = binding.progressBar
        progressBar.visibility = View.VISIBLE

        val api = Retrofit.Builder()
            .baseUrl(com.sid1817733.sid1817733finalassignment.fragments.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequests::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = api.getBooksInfo().awaitResponse()
                if (response.isSuccessful) {
                    val data = response.body()!! //not nulll

                    withContext(Dispatchers.Main) {
                        bookNameText.visibility = View.VISIBLE
                        bookAuthorText.visibility = View.VISIBLE

                        progressBar.visibility = View.GONE
                        val min = 0
                        val max = 11
                        val random: Int = Random().nextInt(max - min + 1) + min
                        bookNameText.text = data.works[random].title
                        bookAuthorText.text = data.works[random].authors[0].name

                    }
                }
            } catch (e: Exception){
                withContext(Dispatchers.Main){
                    //  Log.d("Test", e.toString())
                }
            }
        }

    }
}


