package com.example.musicplayerapp.songs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.musicplayerapp.R
import com.example.musicplayerapp.databinding.ActivitySongPlayerBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.upstream.RawResourceDataSource
import com.google.android.exoplayer2.util.Util

class SongPlayer : AppCompatActivity(),SongPresenter.PlayerView {
    lateinit var binding: ActivitySongPlayerBinding
    private val exoPlayer by lazy { ExoPlayer.Builder(this).build() }
    private var player: ExoPlayer? = null
    private var playWhenReady = true
    private var currentItem = 0
    private var playBackPosition = 0L


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val songID = intent.getIntExtra("SONG_ID", 1)
        val songTitle = intent.getStringExtra("SONG_TITLE")

        setUpExoPLayer(songID, songTitle!!)

    }


    override fun onPause() {
        super.onPause()
        if (Util.SDK_INT <= 23)
            releasePlayer()
    }

    override fun onStop() {
        super.onStop()
        if (Util.SDK_INT > 23)
            releasePlayer()
    }

    private fun releasePlayer() {
        player?.let {
            playWhenReady = exoPlayer.playWhenReady
            currentItem = exoPlayer.currentMediaItemIndex
            playBackPosition = exoPlayer.currentPosition
            exoPlayer.release()
        }
        player = null

    }

    override fun setUpExoPLayer(songId: Int, songTitle: String) {

        player = ExoPlayer.Builder(this).build().also {
            binding.styledPlayer.player = exoPlayer
            val mediaItem =
                MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.music))

            val mediaItem2 =
                MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.blank))

            val mediaItem3 =
                MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.sky_high))


            binding.tvSong.text = songTitle


            when (songId) {
                1 -> {
                    exoPlayer.setMediaItem(mediaItem)
                }

                2 -> {
                    exoPlayer.setMediaItem(mediaItem2)
                }
                3 -> {
                    exoPlayer.setMediaItem(mediaItem3)
                }
            }

            exoPlayer.playWhenReady = playWhenReady
            exoPlayer.seekTo(currentItem,playBackPosition)
            exoPlayer.prepare()
            exoPlayer.play()
        }
    }
}