// Global variable, which is the empty string in production so that
// URLs are relative, and is overridden in tests.
var BaseURL = "";

function rest_match(args, onSuccess, onFailure) {
  $.get(BaseURL + "/rest/match", args, onSuccess)
   .fail(function(jqXHR, textStatus, errorThrown) {
           onFailure(textStatus + ": " + errorThrown);
         });
};

function rest_start(onSuccess) {
  $.get(BaseURL + "/rest/start", onSuccess);
};
