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

class GitHubActivity : AppCompatActivity(), UsersContract.View {

    private lateinit var binding: ActivityGitHubBinding
    private val adapter = UsersAdapter()

    private lateinit var presenter: UsersContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGitHubBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showProgress(true)

        presenter = extractPresenter()
        presenter = UsersPresenter(app.usersRepo)
        presenter.onRefresh()

        initRecyclerView()

        initActon()

        presenter.attach(this)
    }

    private fun extractPresenter(): UsersContract.Presenter {
        return lastCustomNonConfigurationInstance as? UsersContract.Presenter ?: UsersPresenter(app.usersRepo)
    }

    private fun initRecyclerView() {
        binding.usersListRecyclerView.adapter = adapter
    }

    override fun showUsers(users: List<GitUserEntity>) {
        adapter.setData(users)
    }

    override fun showError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }

    override fun showProgress(inProgress: Boolean) {
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
    override fun onRetainCustomNonConfigurationInstance(): UsersContract.Presenter {
        return presenter
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }
}