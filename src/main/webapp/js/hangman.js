// Run when the hangman.html page loads.
// Populates the pattern, enables the submit button, and
// focuses the newGuesses input field.
$(function() {
  rest_start(
    function(thestart) {
      $("#pattern").text(thestart);
      $("#submit").click(guess);
      $("#newGuesses").focus();
    });
});

// Moves newGuesses to the end of oldGuesses and updates pattern
// to reflect all the guesses.
// Called from submit button.
function guess() {
  var oldPattern = $("#pattern").text();
  var oldGuesses = $("#oldGuesses").text();
  var newGuesses = $("#newGuesses").val();
  rest_match(
    { oldPattern: oldPattern, oldGuesses: oldGuesses, newGuesses: newGuesses },
    function(newPattern) {
      $("#pattern").text(newPattern);
      $("#oldGuesses").append(newGuesses);
      $("#newGuesses").val("");
      $("#newGuesses").focus();
    },
    function(failure) {
      $("#fail").text(failure);
    });
};
