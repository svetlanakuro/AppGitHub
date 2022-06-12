package com.svetlanakuro.appgithub.ui.userslist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.svetlanakuro.appgithub.data.MockUsersRepoImpl
import com.svetlanakuro.appgithub.databinding.ActivityGitHubBinding
import com.svetlanakuro.appgithub.domain.entities.UsersRepo

class GitHubActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGitHubBinding
    private val adapter = GitUsersAdapter()
    private val usersRepo: UsersRepo = MockUsersRepoImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGitHubBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter.setData(usersRepo.getUsersList())
        binding.usersListRecyclerView.adapter = adapter
    }
}