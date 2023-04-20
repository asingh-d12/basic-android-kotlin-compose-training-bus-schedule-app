/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.busschedule.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.busschedule.BusScheduleApplication
import com.example.busschedule.data.BusSchedule
import com.example.busschedule.data.BusScheduleRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn

class BusScheduleViewModel(
    private val repo: BusScheduleRepo
) : ViewModel() {
    // Get example bus schedule from Room DB

    val fullScheduleUiStateFlow: StateFlow<BusScheduleUIState> =
        repo.getAllBusSchedules()
            .map { BusScheduleUIState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = BusScheduleUIState()
            )

    fun getFullSchedule(): Flow<List<BusSchedule>> =
        repo.getAllBusSchedules()
            .onEach {
                println("Data collected = $it")
            }


    // Get example bus schedule by stop
    fun getScheduleFor(stopName: String): Flow<List<BusSchedule>> = repo.getBusScheduleByName(stopName)

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                BusScheduleViewModel(app.container.repository)
            }
        }
    }
}

private val CreationExtras.app: BusScheduleApplication
    get() = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as BusScheduleApplication

data class BusScheduleUIState(
    val allSchedule: List<BusSchedule> = listOf()
)