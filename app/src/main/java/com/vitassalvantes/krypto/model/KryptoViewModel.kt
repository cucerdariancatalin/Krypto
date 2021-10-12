package com.vitassalvantes.krypto.model

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.vitassalvantes.krypto.KryptoRoom

/**
 * [KryptoViewModel] holds information about a all rooms and manages it.
 */
class KryptoViewModel : ViewModel() {
    /**
     * List of all rooms
     */
    var listOfRooms = mutableStateListOf<KryptoRoom>()
        private set

    /**
     * Adding a new room to [listOfRooms]
     */
    fun addNewRoom(newRoom: KryptoRoom) {
        listOfRooms.add(newRoom)
    }

    /**
     * Removing a room from [listOfRooms]
     */
    fun removeRoom(roomToRemove: KryptoRoom) {
        listOfRooms.remove(roomToRemove)
    }
}