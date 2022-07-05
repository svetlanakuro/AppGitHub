package com.svetlanakuro.appgithub.ui.userslist

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.svetlanakuro.appgithub.app
import com.svetlanakuro.appgithub.databinding.ActivityGitHubBinding
import com.svetlanakuro.appgithub.domain.entities.GitUserEntity
import com.svetlanakuro.appgithub.ui.userprofile.ProfileActivity

const val EXTRA_DATA = "USER_DATA"

class GitHubActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGitHubBinding
    private val adapter = UsersAdapter()

    private lateinit var viewModel: UsersContract.ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGitHubBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        initViews()
        initActon()
    }

    private fun initViewModel() {
        viewModel = extractViewModel()
        viewModel = UsersViewModel(app.usersRepo)

        viewModel.progressLiveData.observe(this) { showProgress(it) }
        viewModel.usersLiveData.observe(this) { showUsers(it) }
        viewModel.errorLiveData.observe(this) { showError(it) }
    }

    private fun extractViewModel(): UsersContract.ViewModel {
        return lastCustomNonConfigurationInstance as? UsersContract.ViewModel ?: UsersViewModel(app.usersRepo)
    }

    private fun initViews() {
        viewModel.onRefresh()
        binding.usersListRecyclerView.adapter = adapter
    }

    private fun showUsers(users: List<GitUserEntity>) {
        adapter.setData(users)
    }

    private fun showError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }

    private fun showProgress(inProgress: Boolean) {
        binding.progressBar.isVisible = inProgress
        binding.usersListRecyclerView.isVisible = !inProgress
    }

    private fun initActon() {
        adapter.listenerClick = UsersAdapter.OnUserClickListener { user ->
            val intent = Intent(this, ProfileActivity::class.java).apply {
                putExtra(EXTRA_DATA, user)
            }
            startActivity(intent)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onRetainCustomNonConfigurationInstance(): UsersContract.ViewModel {
        return viewModel
    }
}