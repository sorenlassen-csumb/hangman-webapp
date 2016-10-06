package edu.csumb.cst438fa16.hangman.rest;

import edu.csumb.cst438fa16.hangman.Hangman;
import org.glassfish.hk2.api.Factory;

public class HangmanFactory implements Factory<Hangman> {
    static final String HANGMAN_WORD_DEFAULT = "cat";
    static final String HANGMAN_WORD_PROPERTY_KEY = "hangman.word";

    @Override
    public Hangman provide() {
        String word = System.getProperty(HANGMAN_WORD_PROPERTY_KEY,
                                         HANGMAN_WORD_DEFAULT);
        return new Hangman(word);
    }

    @Override
    public void dispose(Hangman hangman) {
    }
}
