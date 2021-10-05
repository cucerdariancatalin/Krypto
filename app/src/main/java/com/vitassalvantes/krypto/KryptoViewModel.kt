package com.vitassalvantes.krypto

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class KryptoViewModel : ViewModel() {
    var listOfRooms = mutableStateListOf<KryptoRoom>()
        private set

    fun addNewRoom(newRoom: KryptoRoom) {
        listOfRooms.add(newRoom)
    }

    fun removeRoom(roomToRemove: KryptoRoom) {
        listOfRooms.remove(roomToRemove)
    }
}