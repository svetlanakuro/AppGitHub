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

class ProfileActivity : AppCompatActivity(), ProfileContract.View {

    private lateinit var binding: ActivityProfileBinding
    private val adapter = UserProjectsAdapter()

    private lateinit var presenter: ProfileContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = intent.getParcelableExtra<GitUserEntity>(EXTRA_DATA)!!

        presenter = extractPresenter()
        presenter = ProfilePresenter(app.usersRepo)
        presenter.onRefresh(user.login)

        initView(user)

        showProgress(true)

        presenter.attach(this)
    }

    private fun extractPresenter(): ProfileContract.Presenter {
        return lastCustomNonConfigurationInstance as? ProfileContract.Presenter
            ?: ProfilePresenter(app.usersRepo)
    }

    private fun initView(user: GitUserEntity) {
        binding.userProfileProjectsRecyclerView.adapter = adapter
        binding.loginUserProfileTextView.text = user.login
        Picasso.get().load(user.avatarUrl).error(R.drawable.ic_baseline_supervised_user_circle_24)
            .into(binding.avatarUserProfileImageView)
    }

    override fun showProfile(userProjects: List<GitProjectsEntity>) {
        adapter.setData(userProjects)
    }

    override fun showError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }

    override fun showProgress(inProgress: Boolean) {
        binding.progressBar.isVisible = inProgress
        binding.userProfileProjectsRecyclerView.isVisible = !inProgress
    }
}