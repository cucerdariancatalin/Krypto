package com.vitassalvantes.krypto.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.vitassalvantes.krypto.R
import com.vitassalvantes.krypto.ciphers.CiphersInfo
import com.vitassalvantes.krypto.data.Correspondence
import com.vitassalvantes.krypto.KryptoViewModel
import com.vitassalvantes.krypto.navigation.KryptoScreen
import com.vitassalvantes.krypto.ui.components.KryptoCard
import com.vitassalvantes.krypto.ui.components.KryptoDialog

/**
 * Screen with a list of created correspondences.
 */
@Composable
fun CorrespondencesScreen(viewModel: KryptoViewModel, navController: NavHostController) {

    // The list of all correspondences
    val listOfAllCorrespondences = viewModel.listOfAllCorrespondences.value

    CorrespondencesScreenContent(
        listOfAllCorrespondences = listOfAllCorrespondences,
        onCardClick = { correspondence ->
            navController.navigate(
                KryptoScreen.CorrespondenceDetailsScreen.route + "/${correspondence.id}"
            ) {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true

                // Restore state when reselecting a previously selected item
                restoreState = false
            }
        },
        getPressedCorrespondence = { id -> viewModel.getCorrespondenceById(id = id) },
        deletePressedCorrespondence = { pressedCorrespondence ->
            viewModel.deleteCurrentCorrespondence(
                correspondence = pressedCorrespondence
            )
        }
    )
}

/**
 * UI content of the [CorrespondencesScreen].
 *
 * @param listOfAllCorrespondences list of all correspondences from the database.
 * @param onCardClick navigation to [CorrespondenceDetailsScreen].
 * @param getPressedCorrespondence get pressed correspondence by id.
 * @param deletePressedCorrespondence delete pressed correspondence.
 */
@Composable
fun CorrespondencesScreenContent(
    listOfAllCorrespondences: List<Correspondence>,
    onCardClick: (Correspondence) -> Unit,
    getPressedCorrespondence: (Int) -> Correspondence,
    deletePressedCorrespondence: (Correspondence) -> Unit
) {
    // Index of long pressed correspondence for dialog
    var pressedCorrespondenceId by rememberSaveable { mutableStateOf(0) }

    // Boolean variable to display the dialog
    var openDialog by rememberSaveable { mutableStateOf(false) }

    // Context for Toast
    val context = LocalContext.current

    // Dialog for deleting the correspondence
    if (openDialog) {
        val pressedCorrespondence = getPressedCorrespondence(pressedCorrespondenceId)
        // All info about selected correspondence
        val correspondenceInfo = stringResource(
            id = R.string.correspondence_info,
            pressedCorrespondence.correspondenceName,
            stringResource(id = pressedCorrespondence.cipherName),
            pressedCorrespondence.key
        )
        // Text with the name of the pressed correspondence for the Toast
        val textByDeleting = stringResource(
            id = R.string.deleted_correspondence,
            pressedCorrespondence.correspondenceName
        )

        KryptoDialog(
            title = stringResource(R.string.delete_this_correspondence),
            text = correspondenceInfo,
            acceptButtonText = stringResource(R.string.remove).uppercase(),
            declineButtonText = stringResource(R.string.cancel).uppercase(),
            onAcceptButtonClick = {
                // Remove pressed correspondence
                Toast.makeText(
                    context,
                    textByDeleting,
                    Toast.LENGTH_SHORT
                ).show()
                deletePressedCorrespondence(pressedCorrespondence)
                openDialog = !openDialog
            },
            onDeclineButtonClick = {
                // Cancel deleting
                openDialog = !openDialog
            },
            onDismissRequest = { openDialog = !openDialog }
        )
    }

    LazyColumn(Modifier.padding(16.dp)) {
        items(listOfAllCorrespondences) { correspondence ->
            KryptoCard(
                cardName = correspondence.correspondenceName,
                cardIcon = CiphersInfo.getCipher(correspondence.cipherName).icon,
                onClickListener = { onCardClick(correspondence) },
                onLongClickListener = {
                    // Call the dialog
                    openDialog = !openDialog
                    pressedCorrespondenceId = correspondence.id
                }
            )
        }
    }
}

@Preview(
    name = "CorrespondencesScreen",
    showSystemUi = true
)
@Composable
fun PreviewCorrespondencesScreen() {
    val testCorrespondence = Correspondence(
        correspondenceName = "TestCorrespondence",
        cipherName = R.string.caesar_cipher_name,
        key = "TestCorrespondence"
    )

    CorrespondencesScreenContent(
        listOfAllCorrespondences = (listOf(
            testCorrespondence,
            testCorrespondence,
            testCorrespondence
        )),
        onCardClick = {},
        getPressedCorrespondence = { testCorrespondence },
        deletePressedCorrespondence = {}
    )
}