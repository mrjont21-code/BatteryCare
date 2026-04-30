package com.batterycare.app.util

import android.content.Context
import android.media.RingtoneManager

class SoundPlayer(private val context: Context) {
    fun playAlertSound() {
        try {
            val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val ringtone = RingtoneManager.getRingtone(context, notification)
            ringtone?.play()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun playChargeCompleteSound() {
        try {
            val alarm = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
            val ringtone = RingtoneManager.getRingtone(context, alarm)
            ringtone?.play()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
