package com.example.demoapp.presentation.match

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapp.R
import com.example.demoapp.databinding.HolderMatcheBinding
import com.example.demoapp.domain.model.Match
import com.example.demoapp.helper.getFormattedDate
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates

class MatchAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var matchList: List<Match> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holderMatchBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.holder_matche, parent, false
        )
        return MatchViewHolder(holderMatchBinding)
    }

    override fun getItemCount(): Int = if (matchList.isNullOrEmpty()) 0 else matchList.size

    private fun getItem(position: Int): Match = matchList[position]

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MatchViewHolder).onBind(getItem(position))
    }

    private inner class MatchViewHolder(private val viewDataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {

        fun onBind(match: Match) {
            (viewDataBinding as HolderMatcheBinding).match = match
            viewDataBinding.isprev = match.winner.isNullOrEmpty()
            viewDataBinding.date = getFormattedDate(match.date)
            viewDataBinding.reminderIcon.setOnClickListener {
                setReminder(match)
            }
            // Just opening via intent but best way to handle with more features can use third party lib like ExoPlayer
            viewDataBinding.highlights.setOnClickListener {
                // open url to play highlights
                match.highlights?.let { url ->
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.setDataAndType(Uri.parse(url), "video/mp4")
                    viewDataBinding.root.context.startActivity(intent)
                }
            }
        }

        /* Function helps to set reminder from opening event calendar to create user want
        *  Reminder suggestion from current time till match day[keeping in mind that match date will be in future]
        *
        * */
        private fun setReminder(match: Match) {
            // Formatting current timestamp and incoming date to readable format
            val yourMilliseconds = System.currentTimeMillis()
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
            val resultDate = Date(yourMilliseconds)
            val startTime = sdf.format(resultDate)
            val endTime = match.date

            // Parsing readable format to be passed to calendar event
            val mStartTime = sdf.parse(startTime)
            val mEndTime = sdf.parse(endTime)

            val mIntent = Intent(Intent.ACTION_EDIT)
            mIntent.type = "vnd.android.cursor.item/event"
            mIntent.putExtra("beginTime", mStartTime?.time)
            mIntent.putExtra("time", true)
            mIntent.putExtra("rule", "FREQ=YEARLY")
            mIntent.putExtra("endTime", mEndTime?.time)
            mIntent.putExtra("title", match.description)
            viewDataBinding.root.context.startActivity(mIntent)
        }
    }

    fun clear() {
        matchList = emptyList()
    }
}