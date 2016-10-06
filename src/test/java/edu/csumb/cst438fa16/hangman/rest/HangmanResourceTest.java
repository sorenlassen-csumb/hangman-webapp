package edu.csumb.cst438fa16.hangman.rest;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

/**
 * See:
 * https://jersey.java.net/documentation/latest/test-framework.html
 * https://jersey.java.net/apidocs/latest/jersey/index.html
 */
public class HangmanResourceTest extends JerseyTest {
    static private final String WORD = "cat";  // the word used in this test

    @Override
    protected Application configure() {
        return new ResourceConfig(HangmanResource.class)
            .register(new AbstractBinder() {
                @Override protected void configure() {
                    bind(WORD).to(String.class).named("word");
                }
            });
    }

    @Test
    public void testStart() {
        WebTarget webTarget = target("start");
        String thestart = webTarget.request().get(String.class);
        assertThat(thestart, equalTo("..."));
    }

    @Test
    public void testMatchWithoutParams() {
        WebTarget webTarget = target("match");
        Response response = webTarget.request().get();
        assertThat(response.getStatus(),
                   equalTo(Response.Status.BAD_REQUEST.getStatusCode()));
    }

    @Test
    public void testMatchWithWrongOldPatternOldGuesses() {
        WebTarget webTarget = target("match").queryParam("oldPattern", ".a.")
                                             .queryParam("oldGuesses", "abc")
                                             .queryParam("newGuesses", "d");
        Response response = webTarget.request().get();
        assertThat(response.getStatus(),
                   equalTo(Response.Status.BAD_REQUEST.getStatusCode()));
    }

    @Test
    public void testMatchWithCorrectOldPatternOldGuesses() {
        WebTarget webTarget = target("match").queryParam("oldPattern", "...")
                                             .queryParam("oldGuesses", "")
                                             .queryParam("newGuesses", "abc");
        String thematch = webTarget.request().get(String.class);
        assertThat(thematch, equalTo("ca."));
    }
}
