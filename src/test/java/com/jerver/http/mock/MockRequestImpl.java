package com.jerver.http.mock;

import com.jerver.http.request.RequestImpl;

import static com.jerver.http.HttpHelper.buildRequestInputStream;

public class MockRequestImpl extends RequestImpl {
    public MockRequestImpl() {
        super();
    }

    public MockRequestImpl(String method, String uri) {
        this(method, uri, null);
    }

    public MockRequestImpl(String method, String uri, String body) {
        super();
        super.parseInputStream(buildRequestInputStream(method, uri, body));
    }
}
