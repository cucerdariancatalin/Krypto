package com.vitassalvantes.krypto;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity to create an encrypted message
 *
 * @author VitasSalvantes
 * @version 6.5
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
        Toast todo_toast; // TODO Realize in the future
        CharSequence todo_message; // TODO Realize in the future

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
     * for transmitting an encrypted message
     *
     * @param view
     */
    public void onEncryptMessage(View view) {
        final EditText inputMessage = (EditText) findViewById(R.id.inputMessage);
        final EditText userKey = (EditText) findViewById(R.id.userKey);
        final Intent intent = new Intent(this, ReceiveMessageActivity.class);
        final Spinner aoc = (Spinner) findViewById(R.id.artOfCipher);
        final String cipher = String.valueOf(aoc.getSelectedItem());
        String outputMessage = "";

        Toast toast; // For debugging
        CharSequence error = "Error"; // For debugging

        switch (cipher) {
            case "Caesar cipher":
                final CaesarCipher cc = new CaesarCipher();

                cc.setInput(String.valueOf(inputMessage.getText()));

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

                outputMessage = cc.encryption(cc.getInput(), cc.getKey());
                intent.putExtra(ReceiveMessageActivity.EXTRA_MESSAGE, outputMessage);
                startActivity(intent);
                break;

            case "Atbash cipher":
                final AtbashCipher ac = new AtbashCipher();

                ac.setInputMessage(String.valueOf(inputMessage.getText()));

                outputMessage = ac.encryption();
                intent.putExtra(ReceiveMessageActivity.EXTRA_MESSAGE, outputMessage);
                startActivity(intent);
                break;

            case "Code word cipher":
                final CodeWordCipher cwc = new CodeWordCipher();

                cwc.setInputMessage(String.valueOf(inputMessage.getText()));
                cwc.setCodeWord(String.valueOf(userKey.getText()));

                outputMessage = cwc.encryption();
                intent.putExtra(ReceiveMessageActivity.EXTRA_MESSAGE, outputMessage);
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