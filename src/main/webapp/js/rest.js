function rest_match(args, onSuccess, onFailure) {
  $.get("/rest/match", args, onSuccess)
   .fail(function(jqXHR, textStatus, errorThrown) {
           onFailure(textStatus + ": " + errorThrown);
         });
};

function rest_start(onSuccess) {
  $.get("/rest/start", onSuccess);
};
