package com.rahat.kitti

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.os.Build
import android.os.IBinder
import android.util.Log

class KittiForegroundService : Service() {

    private val CHANNEL_ID = "kitti_foreground_channel"
    private var recordingThread: Thread? = null
    private var running = false

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notif: Notification = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification.Builder(this, CHANNEL_ID)
                .setContentTitle("kitti is listening")
                .setContentText("Foreground service active")
                .setSmallIcon(android.R.drawable.ic_btn_speak_now)
                .build()
        } else {
            Notification.Builder(this)
                .setContentTitle("kitti is listening")
                .setContentText("Foreground service active")
                .setSmallIcon(android.R.drawable.ic_btn_speak_now)
                .build()
        }

        startForeground(1, notif)
        startRecordingStub()
        return START_STICKY
    }

    override fun onDestroy() {
        stopRecording()
        stopForeground(true)
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val chan = NotificationChannel(CHANNEL_ID, "Kitti service", NotificationManager.IMPORTANCE_LOW)
            val man = getSystemService(NotificationManager::class.java)
            man.createNotificationChannel(chan)
        }
    }

    private fun startRecordingStub() {
        if (running) return
        running = true
        recordingThread = Thread {
            // This is a lightweight audio capture stub. Replace with real hotword or speech engine integration.
            try {
                val sampleRate = 16000
                val bufferSize = AudioRecord.getMinBufferSize(sampleRate, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT)
                val recorder = AudioRecord(MediaRecorder.AudioSource.MIC, sampleRate, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT, bufferSize)
                recorder.startRecording()
                val buffer = ShortArray(bufferSize)
                while (running && recorder.recordingState == AudioRecord.RECORDSTATE_RECORDING) {
                    val read = recorder.read(buffer, 0, buffer.size)
                    if (read > 0) {
                        // TODO: process audio for wake-word / speech-to-text
                    }
                }
                recorder.stop()
                recorder.release()
            } catch (e: Exception) {
                Log.e("KittiService", "Recording error", e)
            }
        }
        recordingThread?.start()
    }

    private fun stopRecording() {
        running = false
        recordingThread?.interrupt()
        recordingThread = null
    }
}
