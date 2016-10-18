// Calls rest/match webapp REST endpoint.
// args is a JavaScript object with fields oldPattern, oldGuesses, newGuesses
// corresponding to the rest/match query parameters.
// Calls onSuccess with new pattern string on success.
// Calls onFailure with failure string on failure.
function rest_match(args, onSuccess, onFailure) {
  $.get("/rest/match", args, onSuccess)
   .fail(function(jqXHR, textStatus, errorThrown) {
           onFailure(textStatus + ": " + errorThrown);
         });
};

// Calls rest/start webapp REST endpoint.
// Calls onSuccess with start pattern string on success.
function rest_start(onSuccess) {
  $.get("/rest/start", onSuccess);
};
