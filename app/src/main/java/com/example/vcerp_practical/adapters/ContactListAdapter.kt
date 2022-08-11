package com.example.vcerp_practical.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vcerp_practical.R
import com.example.vcerp_practical.model.ContactResponse
import java.util.*
import kotlin.collections.ArrayList


class ContactListAdapter(private var lists:ArrayList<ContactResponse>,var onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<ContactListAdapter.DataViewHolder>(),Filterable{

    var contactFilterList = ArrayList<ContactResponse>()

    init {
        contactFilterList = lists
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_contact_list, parent, false))

    override fun getItemCount(): Int = contactFilterList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(contactFilterList[position])
    }

    fun addUsers(list: List<ContactResponse>) {
        this.contactFilterList.apply {
            clear()
            addAll(list)
        }

    }

   inner class DataViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        private var name : TextView = itemView.findViewById(R.id.tvName)
        private var phone : TextView = itemView.findViewById(R.id.tvPhone)
        private var ivDelete : ImageView = itemView.findViewById(R.id.ivDelete)


        fun bind(data: ContactResponse){
            itemView.apply {
                name.setText(data.firstName +" "+data.lastName)
                phone.setText(data.mobile_no)
                ivDelete.setOnClickListener {
                    onItemClickListener.onItemClick(data)
                }
            }

        }
    }

    interface  OnItemClickListener{
        fun onItemClick(contactResponse: ContactResponse)
    }

    fun setDeleteItemClick(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    contactFilterList = lists
                } else {
                    val resultList = ArrayList<ContactResponse>()
                    for (row in lists) {
                        if (row.firstName?.contains(charSearch) == true || row.mobile_no?.contains(charSearch) == true) {
                            resultList.add(row)
                        }
                    }
                    contactFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = contactFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                contactFilterList = results?.values as ArrayList<ContactResponse>
                notifyDataSetChanged()
            }

        }
    }
}