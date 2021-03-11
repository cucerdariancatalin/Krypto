package com.vitassalvantes.krypto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity to create an encrypted message
 *
 * @author VitasSalvantes
 * @version 5.0
 */
public class CreateMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_message);
    }

    /**
     * A method that accepts user input and generates an intent
     * for transmitting an encrypted message
     *
     * @param view
     */
    public void onEncryptMessage(View view) {
        final CaesarCipher cc = new CaesarCipher();
        final AtbashCipher ac = new AtbashCipher();
        final CodeWordCipher cwc = new CodeWordCipher();
        final EditText inputMessage = (EditText) findViewById(R.id.inputMessage);
        final EditText userKey = (EditText) findViewById(R.id.userKey);
        final Intent intent = new Intent(this, ReceiveMessageActivity.class);
        final Spinner aoc = (Spinner) findViewById(R.id.artOfCipher);
        final String cipher = String.valueOf(aoc.getSelectedItem());
        String outputMessage = "";

        switch (cipher) {
            case "Caesar cipher":
                cc.setInput(String.valueOf(inputMessage.getText()));

                try {
                    cc.setKey(Math.abs(Integer.parseInt(String.valueOf(userKey.getText()))));
                } catch (Exception e) {
                    cc.setKey(0);
                    e.printStackTrace();
                }

                outputMessage = cc.encryption(cc.getInput(), cc.getKey());
                intent.putExtra(ReceiveMessageActivity.EXTRA_MESSAGE, outputMessage);
                startActivity(intent);
                break;

            case "Atbash cipher":
                ac.setInputMessage(String.valueOf(inputMessage.getText()));

                outputMessage = ac.encryption();
                intent.putExtra(ReceiveMessageActivity.EXTRA_MESSAGE, outputMessage);
                startActivity(intent);
                break;

            case "Code word cipher":
                cwc.setInputMessage(String.valueOf(inputMessage.getText()));
                try {
                    cwc.setCodeWord(String.valueOf(userKey.getText()));
                } catch (Exception e) {
                    cwc.setCodeWord("");
                    e.printStackTrace();
                }

                outputMessage = cwc.encryption();
                intent.putExtra(ReceiveMessageActivity.EXTRA_MESSAGE, outputMessage);
                startActivity(intent);
                break;

            case "Coming soon...":
                inputMessage.setText(":-)");
                break;

            default:
                inputMessage.setText(R.string.error);
                break;
        }
    }
}
//TODO add new cipher and class with statics methods, ICONS !!!