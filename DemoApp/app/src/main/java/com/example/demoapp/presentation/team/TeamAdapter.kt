package com.example.demoapp.presentation.team

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapp.R
import com.example.demoapp.databinding.HolderTeamBinding
import com.example.demoapp.domain.model.Team
import com.example.demoapp.helper.ID_KEY
import com.example.demoapp.helper.NAME_KEY
import com.example.demoapp.presentation.team.details.TeamDetailsActivity
import kotlin.properties.Delegates

class TeamAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var teamList: List<Team> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holderTeamBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.holder_team, parent, false
        )
        return TeamViewHolder(holderTeamBinding)
    }

    override fun getItemCount(): Int = if (teamList.isNullOrEmpty()) 0 else teamList.size

    private fun getItem(position: Int): Team = teamList[position]

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as TeamViewHolder).onBind(getItem(position))
    }

    private inner class TeamViewHolder(private val viewDataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {

        fun onBind(team: Team) {
            (viewDataBinding as HolderTeamBinding).team = team
            viewDataBinding.root.setOnClickListener {
                //launch details activity
                val intent = Intent(it.context, TeamDetailsActivity::class.java)
                intent.putExtra(ID_KEY, team.id)
                intent.putExtra(NAME_KEY, team.name)
                it.context.startActivity(intent)
            }
        }
    }
}