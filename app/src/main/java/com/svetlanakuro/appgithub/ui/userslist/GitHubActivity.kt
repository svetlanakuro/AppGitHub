package com.svetlanakuro.appgithub.ui.userslist

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.svetlanakuro.appgithub.data.MockUsersRepoImpl
import com.svetlanakuro.appgithub.data.database.RetrofitUsersRepoImpl
import com.svetlanakuro.appgithub.databinding.ActivityGitHubBinding
import com.svetlanakuro.appgithub.domain.UsersRepo

class GitHubActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGitHubBinding
    private val adapter = GitUsersAdapter()
    private val usersRepo: UsersRepo = RetrofitUsersRepoImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGitHubBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showProgress(true)

        usersRepo.getUsers(onSuccess = {
            showProgress(false)
            adapter.setData(it)
        }, onError = {
            showProgress(false)
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
        })

        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.usersListRecyclerView.adapter = adapter
    }

    private fun showProgress(inProgress: Boolean) {
        binding.progressBar.isVisible = inProgress
        binding.usersListRecyclerView.isVisible = !inProgress
    }
}