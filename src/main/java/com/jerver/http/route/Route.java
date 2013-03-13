package com.jerver.http.route;

import com.jerver.http.request.Request;
import com.jerver.http.response.Response;

public interface Route {
    public byte[] resolve(Request request, Response response);
    public String getContentType();
}
