package com.example.demoapp.presentation.team.details

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.demoapp.R
import com.example.demoapp.databinding.ActivityTeamDetialsBinding
import com.example.demoapp.helper.ID_KEY
import com.example.demoapp.helper.NAME_KEY
import com.example.demoapp.helper.isNetworkAvailable
import com.example.demoapp.presentation.match.MatchAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class TeamDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTeamDetialsBinding
    private val teamDetailsViewModel: TeamDetailsViewModel by viewModel()
    private var mAdapter: MatchAdapter? = MatchAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_team_detials)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        intent?.let {
            val id = intent.getStringExtra(ID_KEY) ?: ""
            val name = intent.getStringExtra(NAME_KEY) ?: getString(R.string.app_name)
            supportActionBar?.title = name
            setupData(id)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupData(id: String) {
        binding.matchesRecyclerView.adapter = mAdapter
        if (isNetworkAvailable()) {
            teamDetailsViewModel.getTeamDetails(id)
        } else {
            Toast.makeText(
                this@TeamDetailsActivity,
                getString(R.string.no_internet_connection),
                Toast.LENGTH_SHORT
            ).show()
        }

        with(teamDetailsViewModel) {

            matchesData.observe(this@TeamDetailsActivity) {
                binding.matchProgressBar.visibility = View.GONE
                mAdapter?.matchList = (it.upcoming + it.previous)
            }

            messageData.observe(this@TeamDetailsActivity) {
                Toast.makeText(
                    this@TeamDetailsActivity, it,
                    android.widget.Toast.LENGTH_LONG
                ).show()
            }

            showProgressbar.observe(this@TeamDetailsActivity) { isVisible ->
                binding.matchProgressBar.visibility =
                    if (isVisible) View.VISIBLE else View.GONE
            }
        }
    }
}