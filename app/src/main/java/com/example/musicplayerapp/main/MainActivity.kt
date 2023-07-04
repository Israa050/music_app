package com.example.musicplayerapp.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicplayerapp.databinding.ActivityMainBinding
import com.example.musicplayerapp.model.SongsList
import com.example.musicplayerapp.songs.SongPlayer

class MainActivity : AppCompatActivity(),Presenter.View {

    lateinit var binding: ActivityMainBinding
    lateinit var adapter: SongsListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()
    }

    override fun setUpRecyclerView() {
        binding.rvAllSongs.layoutManager = LinearLayoutManager(this)
        adapter = SongsListAdapter(SongsList.songs){
              val intent = Intent(this, SongPlayer::class.java).apply {
                  putExtra("SONG_ID",it.songId)
                  putExtra("SONG_TITLE",it.songTitle)
                  startActivity(this)
              }
        }
        binding.rvAllSongs.adapter = adapter

    }
}