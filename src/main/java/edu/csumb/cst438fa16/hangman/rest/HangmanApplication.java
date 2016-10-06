package edu.csumb.cst438fa16.hangman.rest;

import edu.csumb.cst438fa16.hangman.Hangman;
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
    public HangmanApplication() {
        register(HangmanResource.class);
        // See: https://jersey.java.net/documentation/latest/ioc.html
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bindFactory(HangmanFactory.class).to(Hangman.class);
            }
        });
    }
}
