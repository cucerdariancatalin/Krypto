package com.vitassalvantes.krypto;

import java.util.LinkedList;

/**
 * Class with methods for encrypting messages with code word cipher
 *
 * @author VitasSalvantes
 * @version 3.0
 */
public class CodeWordCipher {

    /**
     * New alphabet with code word
     */
    private LinkedList<Character> reverseAlphabet;

    /**
     * List of letters of the English alphabet
     */
    final private LinkedList<Character> alphabet = new LinkedList<Character>();

    /**
     * Message from user
     */
    private String inputMessage = "";

    /**
     * En- or decrypted message
     */
    private String outputMessage = "";

    /**
     * Code word with which encryption will be carried out
     */
    private String codeWord = "";

    /**
     * A character array containing the processed custom message
     */
    private char[] messageToChars;

    /**
     * A character array containing the processed code word
     */
    private char[] codeWordToChars;

    /**
     * Method for creating {@link CodeWordCipher#alphabet}
     */
    private void createEnglishAlphabet() {
        for (char letter = 'a'; letter <= 'z'; letter++) {
            alphabet.add(letter);
        }
    }

    /**
     * Method that creates a new alphabet with a {@link CodeWordCipher#codeWord}
     */
    private void createNewAlphabet() {
        reverseAlphabet = new LinkedList<Character>(alphabet);
        for (char c : codeWordToChars) {
            if (reverseAlphabet.contains(c)) {
                reverseAlphabet.remove(reverseAlphabet.indexOf(c)); //TODO
                reverseAlphabet.addFirst(c);
            }
        }
    }

    /**
     * Setter for {@link CodeWordCipher#inputMessage}
     */
    public void setInputMessage(String inputMessage) {
        this.inputMessage = inputMessage.toLowerCase();
    }

    /**
     * Setter for {@link CodeWordCipher#codeWord}
     */
    public void setCodeWord(String codeWord) {
        this.codeWord = codeWord.toLowerCase();
    }

    /**
     * A method for encrypting a user message with code word cipher
     *
     * @return {@link CodeWordCipher#outputMessage}
     */
    String encryption() {
        messageToChars = inputMessage.toCharArray();
        codeWordToChars = new char[codeWord.length()];
        for (int i = codeWord.length() - 1; i >= 0; i--) {
            codeWordToChars[i] = codeWord.charAt(i);
        }

        createEnglishAlphabet();
        createNewAlphabet();

        for (char c : messageToChars) {
            if (alphabet.contains(c)) {
                outputMessage += reverseAlphabet.get(alphabet.indexOf(c));
            } else {
                outputMessage += c;
            }
        }
        return outputMessage;
    }

    /**
     * Method for decrypting a user message with code word cipher
     *
     * @return outputMessage {@link CodeWordCipher#outputMessage}
     */
    String decryption() {
        messageToChars = inputMessage.toCharArray();
        codeWordToChars = new char[codeWord.length()];
        for (int i = codeWord.length() - 1; i >= 0; i--) {
            codeWordToChars[i] = codeWord.charAt(i);
        }

        createEnglishAlphabet();
        createNewAlphabet();

        for (char c : messageToChars) {
            if (reverseAlphabet.contains(c)) {
                outputMessage += alphabet.get(reverseAlphabet.indexOf(c));
            } else {
                outputMessage += c;
            }
        }
        return outputMessage;
    }
}