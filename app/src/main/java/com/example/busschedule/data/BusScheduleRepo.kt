package com.example.busschedule.data

import kotlinx.coroutines.flow.Flow

class BusScheduleRepo(private val busScheduleDao: BusScheduleDao) {

    fun getAllBusSchedules(): Flow<List<BusSchedule>> = busScheduleDao.getAllBusSchedules()

    fun getBusScheduleByName(stopName: String): Flow<List<BusSchedule>> =
        busScheduleDao.getBusScheduleByName(stopName)
}