package com.jerver.http.route;

import com.jerver.http.request.Request;
import com.jerver.http.response.Response;

public interface Routable {
    public void resolve(Request request, Response response);
}
