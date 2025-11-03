package com.example.roomdatabase.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase.data.Person
import com.example.roomdatabase.databinding.ItemPersonBinding


class PersonAdapter(
    private val listener: (Person, String) -> Unit // action type: "edit" or "delete"
) : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    private var list = mutableListOf<Person>()

    inner class PersonViewHolder(val binding: ItemPersonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(person: Person) {
            binding.tvName.text = person.name
            binding.tvEmail.text = person.email

            binding.ivEdit.setOnClickListener { listener(person, "edit") }
            binding.ivDelete.setOnClickListener { listener(person, "delete") }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val binding = ItemPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    fun setData(newList: List<Person>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}