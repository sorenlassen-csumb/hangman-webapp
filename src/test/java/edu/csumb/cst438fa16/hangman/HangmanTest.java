package edu.csumb.cst438fa16.hangman;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

public class HangmanTest {
    @Test
    public void testEmptyWord() {
        Hangman hangman = new Hangman("");
        assertThat(hangman.start(), equalTo(""));
        assertThat(hangman.match("abcdefghijklmnopqrstuvwxyz"), equalTo(""));
    }

    @Test(expected=IllegalArgumentException.class)
    public void testUpperCaseWord() {
        new Hangman("A");
    }

    @Test
    public void testStart() {
        Hangman hangman = new Hangman("cat");
        assertThat(hangman.start(), equalTo("..."));
    }

    @Test
    public void testEmptyMatch() {
        Hangman hangman = new Hangman("cat");
        assertThat(hangman.match(""), equalTo("..."));
    }

    @Test
    public void testMisMatch() {
        Hangman hangman = new Hangman("cat");
        assertThat(hangman.match("xyz"), equalTo("..."));
    }

    @Test
    public void testPartialMatch() {
        Hangman hangman = new Hangman("cat");
        assertThat(hangman.match("abcd"), equalTo("ca."));
    }

    @Test
    public void testAllMatch() {
        Hangman hangman = new Hangman("cat");
        assertThat(hangman.match("catz"), equalTo("cat"));
    }
}
