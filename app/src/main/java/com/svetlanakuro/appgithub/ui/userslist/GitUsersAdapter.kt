package com.svetlanakuro.appgithub.ui.userslist

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.svetlanakuro.appgithub.domain.entities.GitUserEntity

class GitUsersAdapter : RecyclerView.Adapter<GitUsersViewHolder>() {

    private var data: List<GitUserEntity> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(users: List<GitUserEntity>) {
        data = users
        notifyDataSetChanged()
    }

    var listenerClick: OnUserClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitUsersViewHolder {
        return GitUsersViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: GitUsersViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            listenerClick?.onUserClick(getItem(position))
        }
    }

    private fun getItem(position: Int): GitUserEntity = data[position]

    override fun getItemCount(): Int = data.size

    fun interface OnUserClickListener {

        fun onUserClick(user: GitUserEntity)
    }

}