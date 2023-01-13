package com.example.demoapp.presentation.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.demoapp.R
import com.example.demoapp.databinding.TeamFragmentBinding
import com.example.demoapp.helper.isNetworkAvailable
import org.koin.android.viewmodel.ext.android.viewModel

class TeamsFragment(@LayoutRes private val layoutResId: Int) : Fragment() {

    private lateinit var binding: TeamFragmentBinding
    private var mAdapter: TeamAdapter? = TeamAdapter()
    private val teamViewModel: TeamViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupData()
    }

    private fun setupData() {
        binding.teamRecyclerView.adapter = mAdapter

        if (requireContext().isNetworkAvailable()) {
            teamViewModel.getTeams()
        } else {
            Toast.makeText(
                context,
                getString(R.string.no_internet_connection),
                Toast.LENGTH_SHORT
            ).show()
        }

        with(teamViewModel) {

            teamsData.observe(requireActivity()) {
                binding.teamProgressBar.visibility = View.GONE
                mAdapter?.teamList = it
            }

            messageData.observe(requireActivity()) {
                Toast.makeText(
                    context, it,
                    Toast.LENGTH_LONG
                ).show()
            }

            showProgressbar.observe(requireActivity()) { isVisible ->
                binding.teamProgressBar.visibility =
                    if (isVisible) View.VISIBLE else View.GONE
            }
        }
    }

    override fun onDestroyView() {
        mAdapter = null
        super.onDestroyView()

    }
}
