package com.svetlanakuro.appgithub.ui.userprofile

import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.svetlanakuro.appgithub.databinding.ItemProjectsListBinding
import com.svetlanakuro.appgithub.domain.entities.GitProjectsEntity

class UserProjectsViewHolder(private val binding: ItemProjectsListBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {

        fun create(parent: ViewGroup): UserProjectsViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            return UserProjectsViewHolder(ItemProjectsListBinding.inflate(inflater))
        }
    }

    fun bind(item: GitProjectsEntity) {
        binding.titleProjectTextView.text = item.nameProject
        binding.descriptionProjectTextView.text = item.descriptionProject
    }
}