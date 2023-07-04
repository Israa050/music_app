package com.example.musicplayerapp.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayerapp.R
import com.example.musicplayerapp.model.Songs

class SongsListAdapter(var songs : MutableList<Songs>,
                       private val onClick:(Songs)->Unit

): RecyclerView.Adapter<SongsListAdapter.SongViewHolder>() {

    inner class SongViewHolder(
        itemView: View, clickAtPosition: (Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)

        init {
            itemView.setOnClickListener {
                clickAtPosition(absoluteAdapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        return SongViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        ) {
            onClick(songs[it])
        }
    }

    override fun getItemCount(): Int {
        return songs.size
    }


    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        var item = songs[position]
        holder.tvTitle.text = songs[position].songTitle
    }

    private var onItemClickListener : ((Songs) -> Unit)? = null

    fun setOnItemClickListener(listener : ((Songs)->Unit)){
        onItemClickListener = listener
    }

}


