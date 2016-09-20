package edu.csumb.cst438fa16.hangman.rest;

import edu.csumb.cst438fa16.hangman.Hangman;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * Hangman REST service.
 *
 * See:
 * https://jersey.java.net/documentation/latest/jaxrs-resources.html
 */
@Path("")
public class HangmanResource {
    static final String HANGMAN_WORD_DEFAULT = "cat";
    static final String HANGMAN_WORD_PROPERTY_KEY = "hangman.word";

    private static Hangman getHangman() {
        String word = System.getProperty(HANGMAN_WORD_PROPERTY_KEY,
                                         HANGMAN_WORD_DEFAULT);
        return new Hangman(word);
    }

    @Context ServletContext context;

    @GET
    @Path("/start")
    public String start() {
        context.log("start called");
        String pattern = getHangman().start();
        context.log("start returns OK: " + pattern);
        return pattern;
    }

    @GET
    @Path("/match")
    public Response match(
        @QueryParam("oldPattern") String oldPattern,
        @QueryParam("oldGuesses") String oldGuesses,
        @QueryParam("newGuesses") String newGuesses
    ) {
        context.log("match called with oldPattern=" + oldPattern
                                   + " oldGuesses=" + oldGuesses
                                   + " newGuesses=" + newGuesses);
        List<String> missing = new ArrayList<>();
        if (oldPattern == null) missing.add("oldPattern");
        if (oldGuesses == null) missing.add("oldGuesses");
        if (newGuesses == null) missing.add("newGuesses");
        if (!missing.isEmpty()) {
            String msg = "missing parameters " + String.join(", ", missing);
            context.log("match returns Bad Request: " + msg);
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity(msg)
                           .build();
        }

        Hangman hangman = getHangman();
        String oldMatch = hangman.match(oldGuesses);
        if (!oldMatch.equals(oldPattern)) {
            String msg = "oldPattern, oldGuesses don't match the word";
            context.log("match returns Bad Request: oldMatch=" + oldMatch
                        + ", " + msg);
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity(msg)
                           .build();
        }

        String newMatch = hangman.match(oldGuesses + newGuesses);
        context.log("match returns OK: " + newMatch);
        return Response.ok(newMatch).build();
    }
}
