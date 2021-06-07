package com.sid1817733.sid1817733finalassignment.assignments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.sid1817733.sid1817733finalassignment.R
import com.sid1817733.sid1817733finalassignment.assignmentdatabase.Assignment
import com.sid1817733.sid1817733finalassignment.fragments.AssignmentFromModuleFragmentDirections
import kotlinx.android.synthetic.main.custom_row.view.*

class AssignmentListAdapter: RecyclerView.Adapter<AssignmentListAdapter.MyViewHolder>() {



    private var assignmentList = emptyList<Assignment>()
    private lateinit var moduleName: String
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return assignmentList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = assignmentList[position]
        holder.itemView.name_txt.text = currentItem.assignmentName.toString()

        holder.itemView.rowLayout.setOnClickListener {
            val action =
                AssignmentFromModuleFragmentDirections.actionAssignmentFromModuleFragmentToChosenAssignmentFragment(
                    currentItem
                )
            holder.itemView.findNavController().navigate(action)

        }
    }

    fun setData(assignment: List<Assignment>, moduleName: String) {
        this.assignmentList = assignment
        this.moduleName = moduleName
        notifyDataSetChanged()
    }
}