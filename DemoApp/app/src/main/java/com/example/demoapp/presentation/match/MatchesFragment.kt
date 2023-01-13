package com.example.demoapp.presentation.match

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.example.demoapp.R
import com.example.demoapp.databinding.MatchesFragmentBinding
import com.example.demoapp.domain.model.Match
import com.example.demoapp.helper.isNetworkAvailable
import org.koin.android.viewmodel.ext.android.viewModel

class MatchesFragment(@LayoutRes private val layoutResId: Int) : Fragment() {
    private val previous = MutableLiveData<List<Match>>()
    private val upcoming = MutableLiveData<List<Match>>()
    private lateinit var binding: MatchesFragmentBinding
    private val matchViewModel: MatchViewModel by viewModel()
    private var mAdapter: MatchAdapter? = MatchAdapter()

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
        binding.previousMatches.setOnClickListener {
            updateMatchButtonView(R.color.purple_200, R.color.purple_500)
            mAdapter?.let {
                it.clear()
                it.matchList = previous.value.orEmpty()
            }
        }
        binding.upcomingMatches.setOnClickListener {
            updateMatchButtonView(R.color.purple_500, R.color.purple_200)
            mAdapter?.let {
                it.clear()
                it.matchList = upcoming.value.orEmpty()
            }
        }
        setupData()
    }

    // Just using static change better can use selector
    private fun updateMatchButtonView(@ColorRes color1: Int, @ColorRes color2: Int) {
        binding.upcomingMatches.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                color1
            )
        )
        binding.previousMatches.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                color2
            )
        )
    }

    private fun setupData() {
        binding.matchesRecyclerView.adapter = mAdapter
        if (requireContext().isNetworkAvailable()) {
            matchViewModel.getMatches()
        } else {
            Toast.makeText(
                context,
                getString(R.string.no_internet_connection),
                Toast.LENGTH_SHORT
            ).show()
        }

        with(matchViewModel) {

            matchesData.observe(requireActivity()) {
                binding.matchProgressBar.visibility = View.GONE
                previous.value = it.previous.reversed()
                upcoming.value = it.upcoming.reversed()
                mAdapter?.matchList = it.upcoming.reversed()
            }

            messageData.observe(requireActivity()) {
                Toast.makeText(
                    context, it,
                    Toast.LENGTH_LONG
                ).show()
            }

            showProgressbar.observe(requireActivity()) { isVisible ->
                binding.matchProgressBar.visibility =
                    if (isVisible) View.VISIBLE else View.GONE
            }
        }
    }
}