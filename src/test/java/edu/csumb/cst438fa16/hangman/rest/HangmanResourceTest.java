package edu.csumb.cst438fa16.hangman.rest;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

import edu.csumb.cst438fa16.hangman.Hangman;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

/**
 * See:
 * https://jersey.java.net/documentation/latest/test-framework.html
 * https://jersey.java.net/apidocs/latest/jersey/index.html
 */
@RunWith(MockitoJUnitRunner.class)
public class HangmanResourceTest extends JerseyTest {
    @Mock private Hangman hangman;

    @Override
    protected Application configure() {
        //MockitoAnnotations.initMocks(this);
        return new ResourceConfig(HangmanResource.class)
            .register(new AbstractBinder() {
                @Override protected void configure() {
                    bind(hangman).to(Hangman.class);
                }
            });
    }

    @Test
    public void testStart() {
        when(hangman.start()).thenReturn("...");
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
        when(hangman.match("abc")).thenReturn("ca.");
        WebTarget webTarget = target("match").queryParam("oldPattern", ".a.")
                                             .queryParam("oldGuesses", "abc")
                                             .queryParam("newGuesses", "d");
        Response response = webTarget.request().get();
        assertThat(response.getStatus(),
                   equalTo(Response.Status.BAD_REQUEST.getStatusCode()));
    }

    @Test
    public void testMatchWithCorrectOldPatternOldGuesses() {
        when(hangman.match("")).thenReturn("...");
        when(hangman.match("abc")).thenReturn("ca.");
        WebTarget webTarget = target("match").queryParam("oldPattern", "...")
                                             .queryParam("oldGuesses", "")
                                             .queryParam("newGuesses", "abc");
        String thematch = webTarget.request().get(String.class);
        assertThat(thematch, equalTo("ca."));
    }
}
