package com.batterycare.app

import android.app.Application
import com.batterycare.app.data.database.BatteryCareDatabase

class BatteryCareApplication : Application() {

    companion object {
        lateinit var database: BatteryCareDatabase
            private set
    }

    override fun onCreate() {
        super.onCreate()
        database = BatteryCareDatabase.getDatabase(this)
    }
}
