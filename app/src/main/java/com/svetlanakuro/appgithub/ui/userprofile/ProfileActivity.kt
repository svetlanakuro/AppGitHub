package com.svetlanakuro.appgithub.ui.userprofile

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.squareup.picasso.Picasso
import com.svetlanakuro.appgithub.*
import com.svetlanakuro.appgithub.databinding.ActivityProfileBinding
import com.svetlanakuro.appgithub.domain.entities.*
import com.svetlanakuro.appgithub.ui.userslist.EXTRA_DATA

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private val adapter = UserProjectsAdapter()

    private lateinit var viewModel: ProfileContract.ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = intent.getParcelableExtra<GitUserEntity>(EXTRA_DATA)!!

        initViewModel()
        initViews(user)

        showProgress(true)
    }

    private fun initViewModel() {
        viewModel = extractViewModel()
        viewModel = ProfileViewModel(app.usersRepo)

        viewModel.progressLiveData.observe(this) { showProgress(it) }
        viewModel.profileLiveData.observe(this) { showProfile(it) }
        viewModel.errorLiveData.observe(this) { showError(it) }
    }

    private fun extractViewModel(): ProfileContract.ViewModel {
        return lastCustomNonConfigurationInstance as? ProfileContract.ViewModel
            ?: ProfileViewModel(app.usersRepo)
    }

    private fun initViews(user: GitUserEntity) {
        viewModel.onRefresh(user.login)
        binding.userProfileProjectsRecyclerView.adapter = adapter
        binding.loginUserProfileTextView.text = user.login
        Picasso.get().load(user.avatarUrl).error(R.drawable.ic_baseline_supervised_user_circle_24)
            .into(binding.avatarUserProfileImageView)
    }

    private fun showProfile(userProjects: List<GitProjectsEntity>) {
        adapter.setData(userProjects)
    }

    private fun showError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }

    private fun showProgress(inProgress: Boolean) {
        binding.progressBar.isVisible = inProgress
        binding.userProfileProjectsRecyclerView.isVisible = !inProgress
    }

    @Deprecated("Deprecated in Java")
    override fun onRetainCustomNonConfigurationInstance(): ProfileContract.ViewModel {
        return viewModel
    }
}