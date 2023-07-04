package com.example.musicplayerapp.songs

interface SongPresenter {

    interface PlayerView{
        fun setUpExoPLayer(index:Int,Title:String)
    }
}