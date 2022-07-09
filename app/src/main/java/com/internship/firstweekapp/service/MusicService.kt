package com.internship.firstweekapp.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import com.internship.firstweekapp.R

class MusicService : Service() {

    private lateinit var player: MediaPlayer

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        player = MediaPlayer.create(this, R.raw.music)
        player.isLooping = true
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        player.start()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        player.stop()
        super.onDestroy()
    }
}