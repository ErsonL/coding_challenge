package com.wei.challenge.cartrack.ui

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wei.challenge.cartrack.R
import com.wei.challenge.cartrack.model.User

class UsersAdapter(private val onItemClickListener: ((position: Int) -> Unit)) : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    private var items = emptyList<User>()

    fun setItems(items: List<User>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun getItems():List<User> {
        return this.items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.users_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { onItemClickListener(position) }
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
            name.text = Html.fromHtml("<b>Name:</b> ${user.name}")
            userName.text = Html.fromHtml("<b>User Name:</b> ${user.username}")
            email.text = Html.fromHtml("<b>Email:</b> ${user.email}")
            address.text = Html.fromHtml("<b>Address:</b> ${user.address}")
            phone.text = Html.fromHtml("<b>Phone:</b> ${user.phone}")
            website.text = Html.fromHtml("<b>Website:</b> ${user.website}")
            company.text = Html.fromHtml("<b>Company:</b> ${user.company}")
        }
    }


}