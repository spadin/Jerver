package com.jerver.http.mock;

import com.jerver.http.request.Request;

import static com.jerver.http.HttpHelper.buildRequestInputStream;

public class MockRequest extends Request {
    public MockRequest(String method, String uri) {
        super();
        super.parseInputStream(buildRequestInputStream(method, uri));
    }
}
