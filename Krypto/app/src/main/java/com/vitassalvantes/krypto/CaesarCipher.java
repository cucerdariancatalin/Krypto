package com.vitassalvantes.krypto;

import java.util.ArrayList;

/**
 * Class for encrypting messages with Caesar cipher
 *
 * @author VitasSalvantes
 * @version 4.0
 */

public class CaesarCipher {

    /**
     * Message from user
     */
    private String input;

    /**
     * Encrypted message
     */
    private String outputMessage = "";

    /**
     * List of letters of the German alphabet
     */
    private ArrayList<Character> englishAlphabet = new ArrayList<Character>();

    /**
     * A character array containing the processed custom message
     */
    private char[] inputToChars;

    /**
     * Key used for encryption
     */
    private int key;

    /**
     * Method for creating letters of the German alphabet
     *
     * @return deutschAlphabet {@link CaesarCipher#englishAlphabet}
     */
    private void createDeutschAlphabet() {
        for (char letter = 'a'; letter <= 'z'; letter++) {
            englishAlphabet.add(letter);
        }
    }

    /**
     * Method handling custom message
     *
     * @return chars {@link CaesarCipher#inputToChars}
     */
    char[] getInput() {
        inputToChars = input.toCharArray();
        return inputToChars;
    }

    /**
     * Method that receives and processes a custom key
     *
     * @return key {@link CaesarCipher#key}
     */
    int getKey() {
        key %= 25;
        return key;
    }

    /**
     * Setter for input {@link CaesarCipher#input}
     */
    public void setInput(String input) {
        input = input.toLowerCase();
        this.input = input;
    }

    /**
     * Setter for key {@link CaesarCipher#key}
     */
    public void setKey(int key) {
        this.key = key;
    }

    /**
     * A method for encrypting a user message with Caesar cipher
     *
     * @param chars {@link CaesarCipher#inputToChars}
     * @param key   {@link CaesarCipher#key}
     * @return outputMessage {@link CaesarCipher#outputMessage}
     */
    String encryption(char[] chars, int key) {
        createDeutschAlphabet();

        for (char c : chars) {
            if (englishAlphabet.contains(c)) {
                if ((englishAlphabet.indexOf(c) + key) > 25) {
                    outputMessage += englishAlphabet.get(Math.abs(26 - (englishAlphabet.indexOf(c) + key)));
                    continue;
                }
                outputMessage += englishAlphabet.get(englishAlphabet.indexOf(c) + key);

            } else {
                outputMessage += c;
            }
        }
        return outputMessage;
    }

}