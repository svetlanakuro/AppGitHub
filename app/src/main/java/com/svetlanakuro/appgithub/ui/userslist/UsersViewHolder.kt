package com.svetlanakuro.appgithub.ui.userslist

import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.svetlanakuro.appgithub.R
import com.svetlanakuro.appgithub.databinding.ItemUsersListBinding
import com.svetlanakuro.appgithub.domain.entities.GitUserEntity

class UsersViewHolder(private val binding: ItemUsersListBinding) : RecyclerView.ViewHolder(binding.root) {

    companion object {

        fun create(parent: ViewGroup): UsersViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            return UsersViewHolder(ItemUsersListBinding.inflate(inflater))
        }
    }

    fun bind(item: GitUserEntity) {
        Picasso.get().load(item.avatarUrl).error(R.drawable.ic_baseline_supervised_user_circle_24)
            .into(binding.avatarItemUsersListImageView)
        binding.loginUserTextView.text = item.login
        binding.repositoryCounterTextView.text = item.publicRepos.toString()
        binding.followersCounterTextView.text = item.followers.toString()
        binding.followingCounterTextView.text = item.following.toString()
    }
}