// Override global variable from rest.js to point the REST calls to
// the jetty webapp which is running during the integration-test phase.
BaseURL = "http://localhost:8080";

/*
describe('REST match call', function() {
  var newPattern;
  var failure = "none";
  beforeEach(function(done) {
    spyOn($, 'get');
    rest_match(
      { oldPattern: ".a.", oldGuesses: "ab", newGuesses: "c" },
      function(pattern) { newPattern = pattern; done(); },
      function(fail) { failure = fail; done(); }
    );
  });
  it('calls correct URL', function(done) {
    expect(newPattern).toEqual("ca.");
    expect(failure).toEqual("none");
    done();
  });
});
*/

describe('REST start call', function() {
  var startPattern;
  beforeEach(function(done) {
    spyOn($, 'get').and.callThrough();
    rest_start(
      function(pattern) { startPattern = pattern; done(); }
    );
  });
  it('calls correct URL', function() {
    expect(startPattern).toEqual("...");
  });
});
