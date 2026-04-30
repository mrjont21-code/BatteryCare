package com.batterycare.app.util

import android.content.Context
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CheckpointLogger(private val context: Context) {
    private val checkpointFile = File(context.filesDir, "checkpoint.log")
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

    fun writeCheckpoint(level: Int, status: String, health: String) {
        try {
            val timestamp = dateFormat.format(Date())
            val line = "$timestamp | Level: $level% | Status: $status | Health: $health\n"
            checkpointFile.appendText(line)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun readLastCheckpoint(): String? {
        return try {
            if (checkpointFile.exists()) {
                checkpointFile.readLines().lastOrNull()
            } else null
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun clearCheckpoint() {
        try {
            if (checkpointFile.exists()) {
                checkpointFile.delete()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
