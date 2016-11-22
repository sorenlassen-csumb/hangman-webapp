package edu.csumb.cst438fa16.hangman.rest;

import edu.csumb.cst438fa16.hangman.Hangman;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 * Hangman REST service.
 *
 * See:
 * https://jersey.java.net/documentation/latest/jaxrs-resources.html
 */
@Path("")
public class HangmanResource {
    @Inject private Hangman hangman;

    @GET
    @Path("/start")
    public String start() {
        String pattern = hangman.start();
        return pattern;
    }

    @GET
    @Path("/match")
    public Response match(
        @QueryParam("oldPattern") String oldPattern,
        @QueryParam("oldGuesses") String oldGuesses,
        @QueryParam("newGuesses") String newGuesses
    ) {
        List<String> missing = new ArrayList<>();
        if (oldPattern == null) missing.add("oldPattern");
        if (oldGuesses == null) missing.add("oldGuesses");
        if (newGuesses == null) missing.add("newGuesses");
        if (!missing.isEmpty()) {
            String msg = "missing parameters " + String.join(", ", missing);
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity(msg)
                           .build();
        }

        String oldMatch = hangman.match(oldGuesses);
        if (!oldMatch.equals(oldPattern)) {
            String msg = "oldPattern, oldGuesses don't match the word";
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity(msg)
                           .build();
        }

        String newMatch = hangman.match(oldGuesses + newGuesses);
        return Response.ok(newMatch).build();
    }
}
