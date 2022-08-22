package com.internship.firstweekapp.media_player

import android.media.MediaPlayer

class MediaPlayerProvider {
    private var id = -1

    private val player: MediaPlayer = MediaPlayer()

    fun setSource(url: String, id: Int){
        if(id != this.id){
            this.id = id
            player.reset()
            player.setDataSource(url)
            player.prepare()
        }
    }

    fun play(){
        player.start()
    }

    fun pause(){
        player.pause()
    }

    fun stop(){
        player.stop()
    }
}