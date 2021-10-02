package com.vitassalvantes.krypto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Activity to display an encrypted message and send it
 *
 * @author VitasSalvantes
 * @version 6.0
 */

public class ReceiveMessageActivity extends AppCompatActivity {

    /**
     * Name of the transmitted en- or decrypted user message in the intent
     */
    public static final String EXTRA_OUTPUT_MESSAGE = "message";

    /**
     * Encrypted user message
     */
    private String output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_message);

        Intent intent = getIntent();
        TextView outputMessage = (TextView) findViewById(R.id.outputMessage);

        output = intent.getStringExtra(EXTRA_OUTPUT_MESSAGE);
        outputMessage.setText(output);
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
     * Method for creating intent for sending encrypted message to external application
     *
     * @param view
     */
    public void onSendMessage(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        String chooserTitle = getString(R.string.chooser);
        Intent choosenIntent = Intent.createChooser(intent, chooserTitle);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, output);
        startActivity(choosenIntent);
    }
}