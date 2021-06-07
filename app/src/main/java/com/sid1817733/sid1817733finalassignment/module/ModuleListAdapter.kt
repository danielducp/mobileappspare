package com.sid1817733.sid1817733finalassignment.module

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.sid1817733.sid1817733finalassignment.R
import com.sid1817733.sid1817733finalassignment.fragments.ModulesChosenFragmentDirections
import com.sid1817733.sid1817733finalassignment.moduledatabase.Module
import kotlinx.android.synthetic.main.custom_row.view.*

class ModuleListAdapter: RecyclerView.Adapter<ModuleListAdapter.MyViewHolder>() {


    private var moduleList = emptyList<Module>()
    private lateinit var moduleTypeName: String
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return moduleList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = moduleList[position]
        holder.itemView.name_txt.text = currentItem.moduleName.toString()

        holder.itemView.rowLayout.setOnClickListener {
            val action =
                ModulesChosenFragmentDirections.actionModulesChosenFragmentToViewChosenModuleFragment(
                    currentItem
                )
            holder.itemView.findNavController().navigate(action)

        }
    }

    fun setData(module: List<Module>, moduleTypeName: String) {
        this.moduleList = module
        this.moduleTypeName = moduleTypeName
        notifyDataSetChanged()
    }
}