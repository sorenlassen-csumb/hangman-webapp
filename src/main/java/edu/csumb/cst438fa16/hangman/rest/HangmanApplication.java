package edu.csumb.cst438fa16.hangman.rest;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import javax.ws.rs.ApplicationPath;

/**
 * See:
 * https://jersey.java.net/documentation/latest/deployment.html
 * http://buraktas.com/resteasy-example-without-using-a-web-xml/
 * http://stackoverflow.com/a/26721737
 */
@ApplicationPath("rest")
public class HangmanApplication extends ResourceConfig {
    static private final String HANGMAN_WORD_PROPERTY_KEY = "hangman.word";
    static private final String HANGMAN_WORD_DEFAULT = "cat";

    public HangmanApplication() {
        packages("edu.csumb.cst438fa16.hangman.rest");
        // See: https://jersey.java.net/documentation/latest/ioc.html
        register(new AbstractBinder() {
            @Override protected void configure() {
                String word = System.getProperty(HANGMAN_WORD_PROPERTY_KEY,
                                                 HANGMAN_WORD_DEFAULT);
                bind(word).to(String.class).named("word");
            }
        });
    }
}
