package com.vitassalvantes.krypto.model

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.vitassalvantes.krypto.R
import com.vitassalvantes.krypto.data.Correspondence
import com.vitassalvantes.krypto.data.CorrespondenceDao
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * [KryptoViewModel] holds information about a all rooms and manages it.
 */
class KryptoViewModel(private val correspondenceDao: CorrespondenceDao) : ViewModel() {

    /**
     * List of all correspondences
     */
    val listOfAllCorrespondences: MutableState<List<Correspondence>> = mutableStateOf(listOf())

    /**
     * Getting the list of all correspondences from the database at the KryptoViewModel creating
     */
    init {
        viewModelScope.launch {
            correspondenceDao.getAllCorrespondences().collect {
                listOfAllCorrespondences.value = it
            }
        }
    }

    /**
     * Launch coroutine to add the new correspondence
     */
    private fun addCorrespondence(correspondence: Correspondence) {
        viewModelScope.launch {
            correspondenceDao.insert(correspondence)
        }
    }

    /**
     * Launch coroutine to delete the correspondence
     */
    private fun deleteCorrespondence(correspondence: Correspondence) {
        viewModelScope.launch {
            correspondenceDao.delete(correspondence = correspondence)
        }
    }

    /**
     * Add correspondence to the database
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
     * Delete correspondence from the database
     */
    fun deleteCurrentCorrespondence(correspondence: Correspondence) {
        deleteCorrespondence(correspondence = correspondence)
    }

    /**
     * Get correspondence by id from the [listOfAllCorrespondences]
     */
    fun getCorrespondenceById(id: Int): Correspondence =
        listOfAllCorrespondences.value.find { it.id == id }!! // TODO: 24.10.2021 exception

    /**
     * Create new custom correspondence
     *
     * @return true if operation is successful
     */
    fun createNewCorrespondence(
        correspondenceName: String,
        key: String,
        @StringRes cipherName: Int,
        context: Context
    ): Boolean {
        // If the input fields are not empty, then the button will work,
        // else the user will see Toast with tip
        if (correspondenceName.isNotBlank() && key.isNotBlank()) {

            // If the correspondence name already exists, the user will see Toast with tip
            if (listOfAllCorrespondences.value.find { it.correspondenceName == correspondenceName } != null) {
                Toast.makeText(
                    context,
                    context.getString(R.string.this_name_is_used),
                    Toast.LENGTH_SHORT
                ).show()
                return false
            } else {
                addNewCorrespondence(
                    correspondenceName = correspondenceName,
                    cipherName = cipherName,
                    key = key
                )
            }
        } else {
            Toast.makeText(
                context,
                context.getString(R.string.input_fields_are_empty),
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        return true
    }
}

/**
 * A factory for the [KryptoViewModel]
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