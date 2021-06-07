package com.sid1817733.sid1817733finalassignment.note

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.sid1817733.sid1817733finalassignment.R
import com.sid1817733.sid1817733finalassignment.fragments.NotesFromModuleFragmentDirections

import com.sid1817733.sid1817733finalassignment.notedatabase.Note
import kotlinx.android.synthetic.main.custom_row.view.*

class NoteListAdapter: RecyclerView.Adapter<NoteListAdapter.MyViewHolder>() {

    private var noteList = emptyList<Note>()
    private lateinit var moduleName: String

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = noteList[position]
        holder.itemView.name_txt.text = currentItem.noteName.toString()

        holder.itemView.rowLayout.setOnClickListener {
            val action =
                NotesFromModuleFragmentDirections.actionNotesFromModuleFragmentToChosenNoteFragment(
                    currentItem
                )
            holder.itemView.findNavController().navigate(action)

        }
    }

    fun setData(note: List<Note>, moduleName: String) {
        this.noteList = note
        this.moduleName = moduleName

        notifyDataSetChanged()
    }
}