package com.example.demoapp.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.demoapp.R
import com.example.demoapp.presentation.match.MatchesFragment
import com.example.demoapp.presentation.team.TeamsFragment

class ViewPagerAdapter(fm: FragmentManager, var tabCount: Int) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> TeamsFragment(R.layout.team_fragment)
            1 -> MatchesFragment(R.layout.matches_fragment)
            else -> TeamsFragment(R.layout.team_fragment)
        }
    }

    override fun getCount(): Int {
        return tabCount
    }

    override fun getPageTitle(position: Int): CharSequence {
        return "Tab " + (position + 1)
    }
}
