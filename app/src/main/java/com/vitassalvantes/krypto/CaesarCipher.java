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
     * En- or decrypted message
     */
    private String outputMessage = "";

    /**
     * List of letters of the German alphabet
     */
    final private ArrayList<Character> alphabet = new ArrayList<Character>();

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
     * @return deutschAlphabet {@link CaesarCipher#alphabet}
     */
    private void createAlphabet() {
        for (char letter = 'a'; letter <= 'z'; letter++) {
            alphabet.add(letter);
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
        createAlphabet();

        for (char c : chars) {
            if (alphabet.contains(c)) {
                if ((alphabet.indexOf(c) + key) > 25) {
                    outputMessage += alphabet.get(Math.abs(26 - (alphabet.indexOf(c) + key)));
                    continue;
                }
                outputMessage += alphabet.get(alphabet.indexOf(c) + key);

            } else {
                outputMessage += c;
            }
        }
        return outputMessage;
    }

    /**
     * Method for decrypting a user message with Caesar cipher
     *
     * @return outputMessage {@link CaesarCipher#outputMessage}
     */
    String decryption(char[] chars, int key) {
        createAlphabet();

        for (char c : chars) {
            if (alphabet.contains(c)) {
                if ((alphabet.indexOf(c) - key) < 0) {
                    outputMessage += alphabet.get(Math.abs(26 - (key - alphabet.indexOf(c))));
                    continue;
                }
                outputMessage += alphabet.get(alphabet.indexOf(c) - key);

            } else {
                outputMessage += c;
            }
        }
        return outputMessage;
    }
}