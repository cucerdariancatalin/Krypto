package com.vitassalvantes.krypto;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity to create an encrypted message
 *
 * @author VitasSalvantes
 * @version 7.0
 */
public class CreateMessageActivity extends AppCompatActivity {

    /**
     * Method for creating activity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_message);
    }

    /**
     * Method for creating menu
     *
     * @param menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_fast, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Method for handling clicks on menu items
     *
     * @param item
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast todo_toast;
        CharSequence todo_message;

        switch (item.getItemId()) {
            case R.id.action_help:
                todo_message = "Wie funktioniert diese App?";
                todo_toast = Toast.makeText(this, todo_message, Toast.LENGTH_SHORT);
                todo_toast.show();
                return true;

            case R.id.action_theme:
                todo_message = "Wie muss diese App aussehen?";
                todo_toast = Toast.makeText(this, todo_message, Toast.LENGTH_SHORT);
                todo_toast.show();
                return true;

            case R.id.action_language:
                todo_message = "Welche Sprache sprichst du?";
                todo_toast = Toast.makeText(this, todo_message, Toast.LENGTH_SHORT);
                todo_toast.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * A method that accepts user input and generates an intent
     * for going to activity to enter the key or to get the result
     *
     * @param view
     */
    public void onEncryptMessage(View view) {
        final EditText inputMessage = (EditText) findViewById(R.id.inputMessage);
        final ToggleButton action = (ToggleButton) findViewById(R.id.action);
        final Spinner aoc = (Spinner) findViewById(R.id.artOfCipher);
        final String cipher = String.valueOf(aoc.getSelectedItem());
        String outputMessage = "";
        Intent intent;
        Toast toast; // For debugging
        CharSequence error = "Error"; // For debugging

        switch (cipher) {
            case "Caesar cipher":

            case "Code word cipher":
                intent = new Intent(this, KeyInputActivity.class);
                intent.putExtra(KeyInputActivity.EXTRA_INPUT_MESSAGE, String.valueOf(inputMessage.getText()));
                intent.putExtra(KeyInputActivity.EXTRA_CIPHER_TYPE, cipher);
                intent.putExtra(KeyInputActivity.EXTRA_ACTION_TYPE, action.getText());
                startActivity(intent);
                break;

            case "Atbash cipher":
                final AtbashCipher ac = new AtbashCipher();

                ac.setInputMessage(String.valueOf(inputMessage.getText()));

                outputMessage = ac.encryption();
                intent = new Intent(this, ReceiveMessageActivity.class);
                intent.putExtra(ReceiveMessageActivity.EXTRA_OUTPUT_MESSAGE, outputMessage);
                startActivity(intent);
                break;

            case "Coming soon...":
                inputMessage.setText(":-)");
                break;

            default:
                error = "Unknown error";
                toast = Toast.makeText(this, error, Toast.LENGTH_SHORT);
                toast.show();
                break;
        }
    }
}