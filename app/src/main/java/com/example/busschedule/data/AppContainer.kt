package com.example.busschedule.data

import android.content.Context

interface AppContainer {
    val repository: BusScheduleRepo
}

class DefaultAppContainer(private val context: Context) : AppContainer{

    override val repository: BusScheduleRepo =
        BusScheduleRepo(
            BusScheduleDatabase.getDatabase(context = context).busScheduleDao()
        )

}