package com.example.busschedule

import android.app.Application
import com.example.busschedule.data.AppContainer
import com.example.busschedule.data.BusScheduleDatabase
import com.example.busschedule.data.DefaultAppContainer

class BusScheduleApplication: Application() {

    val container: AppContainer by lazy {
        DefaultAppContainer(this)
    }

}