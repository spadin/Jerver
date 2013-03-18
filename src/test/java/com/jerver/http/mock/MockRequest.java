package com.jerver.http.mock;

import com.jerver.http.request.Request;

import static com.jerver.http.HttpHelper.buildRequestInputStream;

public class MockRequest extends Request {
    public MockRequest(String method, String uri) {
        this(method, uri, null);
    }

    public MockRequest(String method, String uri, String body) {
        super();
        super.parseInputStream(buildRequestInputStream(method, uri, body));
    }
}
