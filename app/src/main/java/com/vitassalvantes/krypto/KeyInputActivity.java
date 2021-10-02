package com.vitassalvantes.krypto;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity to get a key
 *
 * @author VitasSalvantes
 * @version 1.5
 */
public class KeyInputActivity extends AppCompatActivity {
    /**
     * Name of the transmitted user input in the intent
     */
    static final String EXTRA_INPUT_MESSAGE = "User input";

    /**
     * Name of the transmitted user cipher in the intent
     */
    static final String EXTRA_CIPHER_TYPE = "Cipher type";

    /**
     * Name of the transmitted user action in the intent
     */
    static final String EXTRA_ACTION_TYPE = "Action type";

    /**
     * User input message
     */
    private String inputMessage = "";

    /**
     * User input cipher
     */
    private String cipherType = "";

    /**
     * User input action
     */
    private String actionType = "";

    /**
     * En- or decrypted user message
     */
    private String outputMessage = "";

    /**
     * Method for creating activity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_input);
        Intent inputIntent = getIntent();
        inputMessage = inputIntent.getStringExtra(EXTRA_INPUT_MESSAGE);
        cipherType = inputIntent.getStringExtra(EXTRA_CIPHER_TYPE);
        actionType = inputIntent.getStringExtra(EXTRA_ACTION_TYPE);
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
     * A method that accepts user key, en- or decrypts a message and generates an intent
     * for going to activity to get the result
     *
     * @param view
     */
    public void onSetKey(View view) {
        final EditText userKey = (EditText) findViewById(R.id.edit_key);
        final Button button = (Button) findViewById(R.id.onSetKey);
        final Intent outputIntent = new Intent(this, ReceiveMessageActivity.class);
        Toast toast; // For debugging
        CharSequence error = "Error"; // For debugging

        switch (cipherType) {
            case "Caesar cipher":
                CaesarCipher cc = new CaesarCipher();
                cc.setInput(inputMessage);

                try {
                    cc.setKey(Math.abs(Integer.parseInt(String.valueOf(userKey.getText()))));
                } catch (Exception e) { // For debugging
                    cc.setKey(0);
                    error = "Schlüssel?";
                    toast = Toast.makeText(this, error, Toast.LENGTH_SHORT);
                    toast.show();
                    e.printStackTrace();
                    break;
                }

                if (actionType.equals(getString(R.string.encrypt))) {
                    outputMessage = cc.encryption(cc.getInput(), cc.getKey());
                } else {
                    outputMessage = cc.decryption(cc.getInput(), cc.getKey());
                }

                outputIntent.putExtra(ReceiveMessageActivity.EXTRA_OUTPUT_MESSAGE, outputMessage);
                startActivity(outputIntent);
                break;

            case "Code word cipher":
                CodeWordCipher cwc = new CodeWordCipher();
                cwc.setCodeWord(String.valueOf(userKey.getText()));
                cwc.setInputMessage(inputMessage);

                if (actionType.equals(getString(R.string.encrypt))) {
                    outputMessage = cwc.encryption();
                } else {
                    outputMessage = cwc.decryption();
                }

                outputIntent.putExtra(ReceiveMessageActivity.EXTRA_OUTPUT_MESSAGE, outputMessage);
                startActivity(outputIntent);
                break;
        }
    }
}