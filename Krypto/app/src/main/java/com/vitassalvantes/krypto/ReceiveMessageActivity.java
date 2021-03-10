package com.vitassalvantes.krypto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Activity to display an encrypted message and send it
 *
 * @author VitasSalvantes
 * @version 5.0
 */

public class ReceiveMessageActivity extends AppCompatActivity {

    /**
     * Name of the transmitted encrypted user message in the intent
     */
    public static final String EXTRA_MESSAGE = "message";

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

        output = intent.getStringExtra(EXTRA_MESSAGE);
        outputMessage.setText(output);
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