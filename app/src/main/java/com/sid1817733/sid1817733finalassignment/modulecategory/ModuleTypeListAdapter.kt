package com.sid1817733.sid1817733finalassignment.modulecategory

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.sid1817733.sid1817733finalassignment.R
import com.sid1817733.sid1817733finalassignment.fragments.ModuleTypeChooseFragmentDirections
import com.sid1817733.sid1817733finalassignment.moduletypedatabase.ModuleType
import kotlinx.android.synthetic.main.custom_row.view.*

class ModuleTypeListAdapter: RecyclerView.Adapter<ModuleTypeListAdapter.MyViewHolder>() {

   private  var moduletypeList = emptyList<ModuleType>()
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))   }

    override fun getItemCount(): Int {
        return moduletypeList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = moduletypeList[position]
        holder.itemView.name_txt.text = currentItem.moduleTypeName

        holder.itemView.rowLayout.setOnClickListener {
            Log.d("My tag",currentItem.moduleTypeName)


            val action = ModuleTypeChooseFragmentDirections.actionModuleTypeChooseFragmentToModulesChosenFragment(currentItem)
            holder.itemView.findNavController().navigate(action)

        }
    }

    fun setData(moduletype: List<ModuleType>)
    {
        this.moduletypeList = moduletype
        notifyDataSetChanged()
    }
}