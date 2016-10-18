// Override global variable from rest.js to point the REST calls to
// the jetty webapp which is running during the integration-test phase.
BaseURL = "http://localhost:8080";

casper.test.begin('assertEquals() test', 1, function(test) {
    test.assertEquals(1 + 1, 2);
    test.done();
});

casper.test.begin('rest_start test', 1, function(test) {
    //phantom.injectJs('/tmp/jquery-3.1.0.js');
    casper.start().then(function() {
        this.setContent('<div id="pattern"></div>');
        rest_start(function(pattern) {
            $("#pattern").text(pattern);
        });
    });
    casper.waitForText("...");
    casper.run(function() { test.done(); });
});
