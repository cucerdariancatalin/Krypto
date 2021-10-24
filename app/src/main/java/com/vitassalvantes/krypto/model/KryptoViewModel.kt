package com.vitassalvantes.krypto.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.vitassalvantes.krypto.data.Correspondence
import com.vitassalvantes.krypto.data.CorrespondenceDao
import kotlinx.coroutines.launch

/**
 * [KryptoViewModel] holds information about a all rooms and manages it.
 */
class KryptoViewModel(private val correspondenceDao: CorrespondenceDao) : ViewModel() {

    /**
     * List of all correspondences
     */
    val listOfAllCorrespondences =
        correspondenceDao.getAllCorrespondences().asLiveData()

    /**
     *
     */
    private fun addCorrespondence(correspondence: Correspondence) {
        viewModelScope.launch {
            correspondenceDao.insert(correspondence)
        }
    }

    /**
     *
     */
    private fun deleteCorrespondence(correspondence: Correspondence) {
        viewModelScope.launch {
            correspondenceDao.delete(correspondence = correspondence)
        }
    }

    /**
     *
     */
    fun addNewCorrespondence(
        correspondenceName: String,
        cipherName: Int,
        key: String
    ) {
        addCorrespondence(
            Correspondence(
                correspondenceName = correspondenceName,
                cipherName = cipherName,
                key = key
            )
        )
    }

    /**
     *
     */
    fun deleteCurrentCorrespondence(correspondence: Correspondence) {
        deleteCorrespondence(correspondence = correspondence)
    }

    /**
     *
     */
    fun findCorrespondenceById(id: Int): Correspondence =
        listOfAllCorrespondences.value!!.find { it.id == id }!! // TODO: 24.10.2021 exception
}

/**
 *
 */
class KryptoViewModelFactory(private val correspondenceDao: CorrespondenceDao) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(KryptoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return KryptoViewModel(correspondenceDao = correspondenceDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}