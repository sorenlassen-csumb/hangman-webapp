// Override global variable from rest.js to point the REST calls to
// the jetty webapp which is running during the integration-test phase.
BaseURL = "http://localhost:8080";

// See: http://jasmine.github.io/2.0/ajax.html
describe('REST start call', function() {
  beforeEach(function() {
    jasmine.Ajax.install();
  });
  afterEach(function() {
    jasmine.Ajax.uninstall();
  });
  it('calls correct URL', function() {
    var onSuccess = jasmine.createSpy("success");
    rest_start(onSuccess);
    expect(jasmine.Ajax.requests.mostRecent().url).toBe(BaseURL + '/rest/start');
    expect(onSuccess).not.toHaveBeenCalled();
    jasmine.Ajax.requests.mostRecent().respondWith({
      "status": 200,
      "contentType": 'text/plain',
      "responseText": '...'
    });
    expect(onSuccess).toHaveBeenCalled();
  });
});
