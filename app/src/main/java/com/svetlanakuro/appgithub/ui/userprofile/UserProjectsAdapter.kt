package com.svetlanakuro.appgithub.ui.userprofile

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.svetlanakuro.appgithub.domain.entities.GitProjectsEntity

class UserProjectsAdapter : RecyclerView.Adapter<UserProjectsViewHolder>() {

    private var data: List<GitProjectsEntity> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(userProjects: List<GitProjectsEntity>) {
        data = userProjects
        notifyDataSetChanged()
    }

    var listenerClick: OnUserClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserProjectsViewHolder {
        return UserProjectsViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: UserProjectsViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            listenerClick?.onUserClick(getItem(position))
        }
    }

    private fun getItem(position: Int): GitProjectsEntity = data[position]

    override fun getItemCount(): Int = data.size

    fun interface OnUserClickListener {

        fun onUserClick(user: GitProjectsEntity)
    }

}