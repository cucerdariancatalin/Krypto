package com.vitassalvantes.krypto;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class for encrypting messages with Atbash cipher
 *
 * @author VitasSalvantes
 * @version 2.0
 */

public class AtbashCipher {

    /**
     * List of letters of the English alphabet
     */
    private ArrayList<Character> englishAlphabet = new ArrayList<Character>();

    /**
     * Message from user
     */
    private String inputMessage;

    /**
     * Encrypted message
     */
    private String outputMessage = "";

    /**
     * A character array containing the processed custom message
     */
    private char[] chars;

    /**
     * Method for creating letters of the English alphabet
     */
    private void createEnglishAlphabet() {
        for (char letter = 'a'; letter <= 'z'; letter++) {
            englishAlphabet.add(letter);
        }
    }

    /**
     * Setter for {@link AtbashCipher#inputMessage} and {@link AtbashCipher#chars}
     */
    public void setInputMessage(String inputMessage) {
        this.inputMessage = inputMessage.toLowerCase();
        chars = inputMessage.toCharArray();
    }

    /**
     * A method for encrypting a user message with Atbash cipher
     */
    String encryption() {
        createEnglishAlphabet();

        for (char c : chars) {
            if (englishAlphabet.contains(c)) {

                outputMessage += englishAlphabet.get(25 - englishAlphabet.indexOf(c));

            } else {
                outputMessage += c;
            }
        }

        return outputMessage;
    }

}