package com.wei.challenge.cartrack.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wei.challenge.cartrack.R
import com.wei.challenge.cartrack.model.User

class UsersAdapter(private val onItemClickListener: ((id: String) -> Unit)) : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    private var items = emptyList<User>()

    fun setItems(items: List<User>) {
        this.items = items
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.users_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { onItemClickListener(item.id) }
    }

    override fun getItemCount(): Int = items.count()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.name)
        private val userName: TextView = itemView.findViewById(R.id.userName)
        private val email: TextView = itemView.findViewById(R.id.email)
        private val address: TextView = itemView.findViewById(R.id.address)
        private val phone: TextView = itemView.findViewById(R.id.phone)
        private val website: TextView = itemView.findViewById(R.id.website)
        private val company: TextView = itemView.findViewById(R.id.company)

        fun bind(user: User) {
            name.text = user.name
            userName.text = user.username
            email.text = user.email
            address.text = user.address.toString()
            phone.text = user.phone
            website.text = user.website
            company.text = user.company.toString()
        }
    }


}